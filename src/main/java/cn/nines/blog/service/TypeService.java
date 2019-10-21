package cn.nines.blog.service;

import cn.nines.blog.pojo.TblTypeInfo;
import cn.nines.blog.util.PageResult;

import java.util.List;

public interface TypeService {

    public TblTypeInfo findByName(String name);

    /**
     * 分页
     * @param pageNum 当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    public PageResult findPage(TblTypeInfo type, int pageNum, int pageSize);

    public int addType(TblTypeInfo type);

    public void deleteType(Long id);

    public void recoverType(Long id);

    public int updateType(TblTypeInfo type);

    public TblTypeInfo findOne(Long id);

    public List<TblTypeInfo> findValid();

    public List<TblTypeInfo> findTop();

}
