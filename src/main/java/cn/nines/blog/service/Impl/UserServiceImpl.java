package cn.nines.blog.service.Impl;

import cn.nines.blog.dao.TblUserMapper;
import cn.nines.blog.pojo.TblUser;
import cn.nines.blog.pojo.TblUserExample;
import cn.nines.blog.service.UserService;
import cn.nines.blog.util.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private TblUserMapper userMapper;

    @Override
    public List<TblUser> findAll() {
        return userMapper.selectByExample(null);
    }

    @Override
    public TblUser findByUsername(String username) {
        TblUserExample example = new TblUserExample();
        TblUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TblUser> users = userMapper.selectByExample(example);
        if (users.size() > 0){
            return users.get(0);
        }
        return null;
    }

    @Override
    public PageResult findPage(TblUser user, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TblUserExample example = new TblUserExample();
        TblUserExample.Criteria criteria = example.createCriteria();

        if (user != null){
            if (user.getNikeName() != null && user.getNikeName().length() > 0){
                criteria.andNikeNameLike("%"+ user.getNikeName()+"%");
            }
            if (user.getIsEffective() != null && user.getIsEffective()){
                criteria.andIsEffectiveEqualTo(true);
            }
        }

        Page<TblUser> page = (Page<TblUser>) userMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public int addUser(TblUser user) {
        String password = DigestUtils.md5Hex(user.getPassword());
        user.setPassword(password);
        user.setUserType(false);
        user.setIsEffective(true);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        return userMapper.insert(user);
    }

    @Override
    public void deleteUser(Long id) {
        TblUser user = userMapper.selectByPrimaryKey(id);
        user.setUpdateTime(new Date());
        user.setIsEffective(false);
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void recoverUser(Long id) {
        TblUser user = userMapper.selectByPrimaryKey(id);
        user.setUpdateTime(new Date());
        user.setIsEffective(true);
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public int updateUser(TblUser user) {
        TblUser oldUser = userMapper.selectByPrimaryKey(user.getId());
        if (oldUser == null){
            return 0;
        }
        user.setUpdateTime(new Date());
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public TblUser findOne(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

}
