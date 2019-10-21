package cn.nines.blog.controller;

import cn.nines.blog.exception.ExceptionEnum;
import cn.nines.blog.pojo.TblUser;
import cn.nines.blog.service.UserService;
import cn.nines.blog.util.JsonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin")
@RestController
public class LoginController {

    @Resource
    private UserService userService;

    /**
     * 用户登录
     * @param user 登录信息
     * @return
     */
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @ApiImplicitParam(name = "user", value = "用户", required = true, dataType = "TblUser", paramType = "body")
    @PostMapping("/login")
    public JsonResult login(@RequestBody TblUser user){

        TblUser oldUser = userService.findByUsername(user.getUsername());
        if (oldUser == null){
            return new JsonResult(ExceptionEnum.USERNAME_NOT_EXIST);
        }
        if (!oldUser.getIsEffective()){
            return new JsonResult(ExceptionEnum.ACCOUNT_IS_FROZEN);
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), DigestUtils.md5Hex(user.getPassword()));
        try {
            subject.login(token);
            return new JsonResult();
        }catch (UnknownAccountException uae){
            //username 不存在，给个错误提示?
            return new JsonResult(ExceptionEnum.USERNAME_NOT_EXIST);
        }catch (IncorrectCredentialsException ice){
            //password 不匹配，再输入?
            return new JsonResult(ExceptionEnum.PASSWORD_NOT_MATCHING);
        }catch (LockedAccountException lae){
            //账号锁住了，不能登入。给个提示?
            return new JsonResult(ExceptionEnum.ACCOUNT_IS_LOCKED);
        }catch (AuthenticationException ae){
            //未考虑到的问题 - 错误?
            return new JsonResult(ExceptionEnum.PLEASE_TRY_AGAIN);
        }
    }

    /**
     * 退出登录
     * @return
     */
    @ApiOperation(value = "注销", notes = "退出登录")
    @GetMapping("/logout")
    public JsonResult logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new JsonResult(201, "退出成功");
    }

    /**
     * 获取登录用户的昵称
     * @return 现登录账户昵称
     */
    @ApiOperation(value = "获取昵称", notes = "获取登录用户的昵称")
    @GetMapping("/getLoginInfo")
    public Map getLoginInfo(){
        TblUser user = (TblUser) SecurityUtils.getSubject().getPrincipal();
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", user.getNikeName());
        map.put("email", user.getEmail());
        map.put("chatHead", user.getChatHead());
        return map;
    }

}
