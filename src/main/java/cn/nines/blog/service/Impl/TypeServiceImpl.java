package cn.nines.blog.service.Impl;

import cn.nines.blog.dao.TblTypeInfoMapper;
import cn.nines.blog.pojo.TblTypeInfo;
import cn.nines.blog.pojo.TblTypeInfoExample;
import cn.nines.blog.service.TypeService;
import cn.nines.blog.util.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {

    @Resource
    private TblTypeInfoMapper typeMapper;

    @Override
    public TblTypeInfo findByName(String name) {
        TblTypeInfoExample example = new TblTypeInfoExample();
        TblTypeInfoExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<TblTypeInfo> types = typeMapper.selectByExample(example);
        if (types.size() > 0){
            return types.get(0);
        }
        return null;
    }

    @Override
    public PageResult findPage(TblTypeInfo type, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TblTypeInfoExample example = new TblTypeInfoExample();
        TblTypeInfoExample.Criteria criteria = example.createCriteria();

        if (type != null){
            if (type.getName() != null && type.getName().length() > 0){
                criteria.andNameLike("%" + type.getName() + "%");
            }
            if (type.getIsEffective() != null && type.getIsEffective()){
                criteria.andIsEffectiveEqualTo(true);
            }
        }

        Page<TblTypeInfo> page = (Page<TblTypeInfo>) typeMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public int addType(TblTypeInfo type) {
        type.setIsEffective(true);
        type.setNum((byte) 0);
        type.setCreateTime(new Date());
        type.setUpdateTime(new Date());
        return typeMapper.insert(type);
    }

    @Override
    public void deleteType(Long id) {
        TblTypeInfo type = typeMapper.selectByPrimaryKey(id);
        type.setIsEffective(false);
        type.setUpdateTime(new Date());
        typeMapper.updateByPrimaryKey(type);
    }

    @Override
    public void recoverType(Long id) {
        TblTypeInfo type = typeMapper.selectByPrimaryKey(id);
        type.setUpdateTime(new Date());
        type.setIsEffective(true);
        typeMapper.updateByPrimaryKey(type);
    }

    @Override
    public int updateType(TblTypeInfo type) {
        TblTypeInfo oldType = typeMapper.selectByPrimaryKey(type.getId());
        if (oldType == null){
            return 0;
        }
        type.setUpdateTime(new Date());
        return typeMapper.updateByPrimaryKey(type);
    }

    @Override
    public TblTypeInfo findOne(Long id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TblTypeInfo> findValid() {
        TblTypeInfoExample example = new TblTypeInfoExample();
        TblTypeInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIsEffectiveEqualTo(true);
        return typeMapper.selectByExample(example);
    }

    @Override
    public List<TblTypeInfo> findTop() {
        PageHelper.startPage(1, 6);
        TblTypeInfoExample example = new TblTypeInfoExample();
        example.setOrderByClause("num Desc");
        Page<TblTypeInfo> page = (Page<TblTypeInfo>) typeMapper.selectByExample(example);
        return page.getResult();
    }
}
