package cn.nines.blog.service.Impl;

import cn.nines.blog.dao.SysLogMapper;
import cn.nines.blog.pojo.SysLog;
import cn.nines.blog.pojo.SysLogExample;
import cn.nines.blog.service.LogService;
import cn.nines.blog.util.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class LogServiceImpl implements LogService {

    @Resource
    private SysLogMapper logMapper;

    @Override
    public PageResult findPage(SysLog log, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        SysLogExample example = new SysLogExample();
        example.setOrderByClause("operate_time DESC");
        SysLogExample.Criteria criteria = example.createCriteria();

        if (log != null){
            if (log.getIp() != null && log.getIp().length() > 0){
                criteria.andIpEqualTo(log.getIp());
            }
        }

        Page<SysLog> page = (Page<SysLog>) logMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void addLog(SysLog log) {
        logMapper.insert(log);
    }
}
