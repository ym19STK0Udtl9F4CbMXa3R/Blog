package cn.nines.blog.controller;

import cn.nines.blog.exception.ExceptionEnum;
import cn.nines.blog.pojo.TblUser;
import cn.nines.blog.service.UserService;
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
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService userService;

    /**
     * 获取所有用户
     * @return
     */
    @ApiOperation(value = "获取所有用户", notes = "获取所有用户信息")
    @GetMapping("/users/findAll")
    public List<TblUser> findAll(){
        List<TblUser> users = userService.findAll();
        System.out.println(users);
        return users;
    }

    @ApiOperation(value = "获取用户信息", notes = "根据条件分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户", required = false, dataType = "TblUser", paramType = "body"),
            @ApiImplicitParam(name = "num", value = "当前页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = false, dataType = "int", paramType = "path")
    })
    @PostMapping("/users/search")
    public PageResult search(@RequestBody TblUser user, int num, int size){
        return userService.findPage(user, num, size);
    }


    @ApiOperation(value = "添加用户", notes = "添加用户信息")
    @ApiImplicitParam(name = "user", value = "用户", required = true, dataType = "TblUser", paramType = "body")
    @PostMapping("/users")
    public JsonResult addUser(@RequestBody TblUser user){
        try {
            userService.addUser(user);
            return new JsonResult();
        }catch (Exception e){
            logger.error(e.getMessage());
            return new JsonResult(ExceptionEnum.INSERT_EXECUTION_FAILED);
        }
    }

    @ApiOperation(value = "冻结用户", notes = "冻结用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, defaultValue = "long", paramType = "path")
    @DeleteMapping("/users/{id}")
    public JsonResult deleteUser(@PathVariable Long id){
        try {
            userService.deleteUser(id);
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(ExceptionEnum.DELECT_EXECUTION_FAILED);
        }
    }

    @ApiOperation(value = "恢复用户", notes = "恢复用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, defaultValue = "long", paramType = "path")
    @PutMapping("/users/{id}")
    public JsonResult recoverUser(@PathVariable Long id){
        try {
            userService.recoverUser(id);
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(ExceptionEnum.RECOVER_EXECUTION_FAILED);
        }
    }

    @ApiOperation(value = "修改用户", notes = "更新用户信息")
    @ApiImplicitParam(name = "user", value = "用户", required = true, defaultValue = "TblUser", paramType = "body")
    @PutMapping("/users")
    public JsonResult updateUser(@RequestBody TblUser user){
        try {
            int result = userService.updateUser(user);
            if (result == 0){
                return new JsonResult(ExceptionEnum.DATE_EXCEPTION);
            }
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(ExceptionEnum.UPDATE_EXECUTION_FAILED);
        }
    }

    @ApiOperation(value = "获取用户", notes = "根据id获取用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, defaultValue = "long", paramType = "path")
    @GetMapping("/users/{id}")
    public TblUser findOne(@PathVariable Long id){
        try {
            return userService.findOne(id);
        }catch (Exception e){
            return null;
        }
    }

}
