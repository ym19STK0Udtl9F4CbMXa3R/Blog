package cn.nines.blog.service.Impl;

import cn.nines.blog.dao.SysViewMapper;
import cn.nines.blog.pojo.SysView;
import cn.nines.blog.service.ViewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class ViewServiceImpl implements ViewService {

    @Resource
    private SysViewMapper viewMapper;

    @Override
    public void addView(SysView view) {
        viewMapper.insert(view);
    }
}
