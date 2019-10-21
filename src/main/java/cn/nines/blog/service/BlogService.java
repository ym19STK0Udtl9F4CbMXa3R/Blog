package cn.nines.blog.service;

import cn.nines.blog.pojo.TblArticleInfo;
import cn.nines.blog.pojo.TblTypeInfo;
import cn.nines.blog.pojogroup.Articles;
import cn.nines.blog.util.PageResult;

import java.util.List;
import java.util.Map;

public interface BlogService {

    /**
     * 分页
     * @param pageNum 当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    public PageResult findPage(TblArticleInfo article, int pageNum, int pageSize);

    public PageResult findPage(int pageNum, int pageSize);

    public PageResult findPage(Long tagId, int pageNum, int pageSize);

    public PageResult findPage(String searchData, int pageNum, int pageSize);

    public void addArticle(Articles article);

    public void publishArticle(TblArticleInfo article);

    public void deleteArticle(Long id);

    public void recoverArticle(Long id);

    public void updateArticle(Articles article);

    public Articles findOne(Long id);

    public List<TblArticleInfo> findTop();

    public void updateViews(Long id);

    public List<String> getYears();

    public List findArchiveBlogByYears(List<String> years);

}
