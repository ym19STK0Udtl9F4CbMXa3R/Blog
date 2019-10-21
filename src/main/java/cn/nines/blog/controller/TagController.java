package cn.nines.blog.controller;

import cn.nines.blog.exception.ExceptionEnum;
import cn.nines.blog.pojo.TblTagInfo;
import cn.nines.blog.service.TagService;
import cn.nines.blog.util.JsonResult;
import cn.nines.blog.util.PageResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class TagController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TagService tagService;

    @ApiOperation(value = "获取分类信息", notes = "根据条件分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tag", value = "分类", required = false, dataType = "TblTagInfo", paramType = "body"),
            @ApiImplicitParam(name = "num", value = "当前页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = false, dataType = "int", paramType = "path")
    })
    @PostMapping("/tags/search")
    public PageResult search(@RequestBody TblTagInfo tag, int num, int size){
        return tagService.findPage(tag, num, size);
    }

    @ApiOperation(value = "添加分类", notes = "添加分类信息")
    @ApiImplicitParam(name = "tag", value = "分类", required = true, dataType = "TblTagInfo", paramType = "body")
    @PostMapping("/tags")
    public JsonResult addTag(@RequestBody TblTagInfo tag){
        try {
            TblTagInfo oldtag = tagService.findByName(tag.getName());
            if (oldtag != null){
                return new JsonResult(ExceptionEnum.DATA_EXIST);
            }
            tagService.addTag(tag);
            return new JsonResult();
        }catch (Exception e){
            logger.error(e.getMessage());
            return new JsonResult(ExceptionEnum.INSERT_EXECUTION_FAILED);
        }
    }

    @ApiOperation(value = "删除分类", notes = "删除分类信息")
    @ApiImplicitParam(name = "id", value = "分类id", required = true, defaultValue = "long", paramType = "path")
    @DeleteMapping("/tags/{id}")
    public JsonResult deleteTag(@PathVariable Long id){
        try {
            tagService.deleteTag(id);
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(ExceptionEnum.DELECT_EXECUTION_FAILED);
        }
    }

    @ApiOperation(value = "恢复分类", notes = "恢复分类信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, defaultValue = "long", paramType = "path")
    @PutMapping("/tags/{id}")
    public JsonResult recoverTag(@PathVariable Long id){
        try {
            tagService.recoverTag(id);
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(ExceptionEnum.RECOVER_EXECUTION_FAILED);
        }
    }

    @ApiOperation(value = "修改用户", notes = "更新用户信息")
    @ApiImplicitParam(name = "user", value = "用户", required = true, defaultValue = "TblUser", paramType = "body")
    @PutMapping("/tags")
    public JsonResult updateTag(@RequestBody TblTagInfo tag){
        try {
            int result = tagService.updateTag(tag);
            if (result == 0){
                return new JsonResult(ExceptionEnum.DATE_EXCEPTION);
            }
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(ExceptionEnum.UPDATE_EXECUTION_FAILED);
        }
    }

    @ApiOperation(value = "获取分类", notes = "根据id获取分类信息")
    @ApiImplicitParam(name = "id", value = "分类id", required = true, defaultValue = "long", paramType = "path")
    @GetMapping("/tags/{id}")
    public TblTagInfo findOne(@PathVariable Long id){
        try {
            return tagService.findOne(id);
        }catch (Exception e){
            return null;
        }
    }

    @ApiOperation(value = "获取标签", notes = "获取所有有效标签信息")
    @GetMapping("/tags")
    public List<TblTagInfo> findValid(){
        try {
            return tagService.findValid();
        }catch (Exception e){
            return null;
        }
    }

}
