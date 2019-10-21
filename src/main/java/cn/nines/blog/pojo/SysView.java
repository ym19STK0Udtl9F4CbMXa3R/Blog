package cn.nines.blog.pojo;

import java.util.Date;

public class SysView {
    private Long id;

    private String visitIp;

    private Date visitTime;

    public SysView() {
    }

    public SysView(String visitIp, Date visitTime) {
        this.visitIp = visitIp;
        this.visitTime = visitTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVisitIp() {
        return visitIp;
    }

    public void setVisitIp(String visitIp) {
        this.visitIp = visitIp == null ? null : visitIp.trim();
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }
}