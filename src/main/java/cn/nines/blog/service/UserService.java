package cn.nines.blog.service;

import cn.nines.blog.pojo.TblUser;
import cn.nines.blog.util.PageResult;

import java.util.List;

public interface UserService {

    public List<TblUser> findAll();

    public TblUser findByUsername(String username);

    /**
     * 分页
     * @param pageNum 当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    public PageResult findPage(TblUser user, int pageNum, int pageSize);

    public int addUser(TblUser user);

    public void deleteUser(Long id);

    public void recoverUser(Long id);

    public int updateUser(TblUser user);

    public TblUser findOne(Long id);

}
