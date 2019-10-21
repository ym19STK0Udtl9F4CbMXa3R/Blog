package cn.nines.blog.pojo;

import java.util.Date;

public class TblArticleTag {
    private Long id;

    private Long articleId;

    private Long tagId;

    private Date createTime;

    private Date updateTime;

    public TblArticleTag() {
    }

    public TblArticleTag(Long articleId, Long tagId, Date createTime, Date updateTime) {
        this.articleId = articleId;
        this.tagId = tagId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}