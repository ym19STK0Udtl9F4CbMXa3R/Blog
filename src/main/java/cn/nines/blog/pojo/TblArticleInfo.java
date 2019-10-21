package cn.nines.blog.pojo;

import java.util.Date;

public class TblArticleInfo {
    private Long id;

    private String title;

    private String firstPicture;

    private Long authorId;

    private String authorNikeName;

    private String authorChatHead;

    private String summary;

    private Long typeId;

    private String flag;

    private Integer views;

    private Boolean isAppreciation;

    private Boolean isShareStatement;

    private Boolean isCommentabled;

    private Boolean isPublished;

    private Boolean isRecommend;

    private Boolean isEffective;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture == null ? null : firstPicture.trim();
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorNikeName() {
        return authorNikeName;
    }

    public void setAuthorNikeName(String authorNikeName) {
        this.authorNikeName = authorNikeName == null ? null : authorNikeName.trim();
    }

    public String getAuthorChatHead() {
        return authorChatHead;
    }

    public void setAuthorChatHead(String authorChatHead) {
        this.authorChatHead = authorChatHead == null ? null : authorChatHead.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Boolean getIsAppreciation() {
        return isAppreciation;
    }

    public void setIsAppreciation(Boolean isAppreciation) {
        this.isAppreciation = isAppreciation;
    }

    public Boolean getIsShareStatement() {
        return isShareStatement;
    }

    public void setIsShareStatement(Boolean isShareStatement) {
        this.isShareStatement = isShareStatement;
    }

    public Boolean getIsCommentabled() {
        return isCommentabled;
    }

    public void setIsCommentabled(Boolean isCommentabled) {
        this.isCommentabled = isCommentabled;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

    public Boolean getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Boolean isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Boolean getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(Boolean isEffective) {
        this.isEffective = isEffective;
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