package cn.nines.blog.pojo;

import java.util.Date;

public class SysLog {
    private Long id;

    private String ip;

    private Date operateTime;

    private String operateContent;

    private String operateUrl;

    private String operateBy;

    public SysLog() {
    }

    public SysLog(String ip, Date operateTime, String operateContent, String operateUrl, String operateBy) {
        this.ip = ip;
        this.operateTime = operateTime;
        this.operateContent = operateContent;
        this.operateUrl = operateUrl;
        this.operateBy = operateBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateContent() {
        return operateContent;
    }

    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent == null ? null : operateContent.trim();
    }

    public String getOperateUrl() {
        return operateUrl;
    }

    public void setOperateUrl(String operateUrl) {
        this.operateUrl = operateUrl == null ? null : operateUrl.trim();
    }

    public String getOperateBy() {
        return operateBy;
    }

    public void setOperateBy(String operateBy) {
        this.operateBy = operateBy == null ? null : operateBy.trim();
    }
}