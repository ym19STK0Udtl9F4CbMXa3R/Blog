package cn.nines.blog.controller;

import cn.nines.blog.pojo.SysLog;
import cn.nines.blog.service.BlogService;
import cn.nines.blog.service.LogService;
import cn.nines.blog.util.PageResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class LogController {

    @Resource
    private LogService logService;

    @ApiOperation(value = "获取日志信息", notes = "根据条件分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "log", value = "日志", required = false, dataType = "SysLog", paramType = "body"),
            @ApiImplicitParam(name = "num", value = "当前页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = false, dataType = "int", paramType = "path")
    })
    @PostMapping("/logs/search")
    public PageResult search(@RequestBody SysLog log, int num, int size){
        return logService.findPage(log, num, size);
    }

}
