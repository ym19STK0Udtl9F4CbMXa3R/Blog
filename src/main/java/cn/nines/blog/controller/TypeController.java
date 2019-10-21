package cn.nines.blog.controller;

import cn.nines.blog.exception.ExceptionEnum;
import cn.nines.blog.pojo.TblTypeInfo;
import cn.nines.blog.service.TypeService;
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
public class TypeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TypeService typeService;

    @ApiOperation(value = "获取分类信息", notes = "根据条件分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "分类", required = false, dataType = "TblTypeInfo", paramType = "body"),
            @ApiImplicitParam(name = "num", value = "当前页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = false, dataType = "int", paramType = "path")
    })
    @PostMapping("/types/search")
    public PageResult search(@RequestBody TblTypeInfo type, int num, int size){
        return typeService.findPage(type, num, size);
    }

    @ApiOperation(value = "添加分类", notes = "添加分类信息")
    @ApiImplicitParam(name = "type", value = "分类", required = true, dataType = "TblTypeInfo", paramType = "body")
    @PostMapping("/types")
    public JsonResult addType(@RequestBody TblTypeInfo type){
        try {
            TblTypeInfo oldType = typeService.findByName(type.getName());
            if (oldType != null){
                return new JsonResult(ExceptionEnum.DATA_EXIST);
            }
            typeService.addType(type);
            return new JsonResult();
        }catch (Exception e){
            logger.error(e.getMessage());
            return new JsonResult(ExceptionEnum.INSERT_EXECUTION_FAILED);
        }
    }

    @ApiOperation(value = "删除分类", notes = "删除分类信息")
    @ApiImplicitParam(name = "id", value = "分类id", required = true, defaultValue = "long", paramType = "path")
    @DeleteMapping("/types/{id}")
    public JsonResult deleteType(@PathVariable Long id){
        try {
            typeService.deleteType(id);
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(ExceptionEnum.DELECT_EXECUTION_FAILED);
        }
    }

    @ApiOperation(value = "恢复分类", notes = "恢复分类信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, defaultValue = "long", paramType = "path")
    @PutMapping("/types/{id}")
    public JsonResult recoverType(@PathVariable Long id){
        try {
            typeService.recoverType(id);
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(ExceptionEnum.RECOVER_EXECUTION_FAILED);
        }
    }

    @ApiOperation(value = "修改用户", notes = "更新用户信息")
    @ApiImplicitParam(name = "user", value = "用户", required = true, defaultValue = "TblUser", paramType = "body")
    @PutMapping("/types")
    public JsonResult updateType(@RequestBody TblTypeInfo type){
        try {
            int result = typeService.updateType(type);
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
    @GetMapping("/types/{id}")
    public TblTypeInfo findOne(@PathVariable Long id){
        try {
            return typeService.findOne(id);
        }catch (Exception e){
            return null;
        }
    }

    @ApiOperation(value = "获取分类", notes = "获取所有有效分类信息")
    @GetMapping("/types")
    public List<TblTypeInfo> findValid(){
        try {
            return typeService.findValid();
        }catch (Exception e){
            return null;
        }
    }

}
