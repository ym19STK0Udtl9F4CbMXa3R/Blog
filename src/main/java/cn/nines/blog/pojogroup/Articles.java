package cn.nines.blog.pojogroup;

import cn.nines.blog.pojo.TblArticleContent;
import cn.nines.blog.pojo.TblArticleInfo;

import java.util.List;

public class Articles {

    private TblArticleInfo articleInfo;

    private TblArticleContent articleContent;

    private List<Long> tagIds;

    public Articles() {
    }

    public Articles(TblArticleInfo articleInfo, TblArticleContent articleContent, List<Long> tagIds) {
        this.articleInfo = articleInfo;
        this.articleContent = articleContent;
        this.tagIds = tagIds;
    }

    public TblArticleInfo getArticleInfo() {
        return articleInfo;
    }

    public void setArticleInfo(TblArticleInfo articleInfo) {
        this.articleInfo = articleInfo;
    }

    public TblArticleContent getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(TblArticleContent articleContent) {
        this.articleContent = articleContent;
    }

    public List<Long> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
    }
}
