package cn.nines.blog.service;

import cn.nines.blog.pojo.SysLog;
import cn.nines.blog.util.PageResult;

import javax.annotation.Resource;

public interface LogService {

    /**
     * 分页
     * @param pageNum 当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    public PageResult findPage(SysLog log, int pageNum, int pageSize);

    public void addLog(SysLog log);

}
