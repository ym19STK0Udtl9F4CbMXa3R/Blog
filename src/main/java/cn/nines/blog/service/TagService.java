package cn.nines.blog.service;

import cn.nines.blog.pojo.TblTagInfo;
import cn.nines.blog.util.PageResult;

import java.util.List;
import java.util.Map;

public interface TagService {

    public TblTagInfo findByName(String name);

    /**
     * 分页
     * @param pageNum 当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    public PageResult findPage(TblTagInfo tag, int pageNum, int pageSize);

    public int addTag(TblTagInfo tag);

    public void deleteTag(Long id);

    public void recoverTag(Long id);

    public int updateTag(TblTagInfo tag);

    public TblTagInfo findOne(Long id);

    public List<TblTagInfo> findValid();

    public List<TblTagInfo> findTop();

    public List findByArticleIds(List<Long> articleIds);

}
