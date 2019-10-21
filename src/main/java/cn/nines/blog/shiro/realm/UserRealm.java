package cn.nines.blog.shiro.realm;

import cn.nines.blog.pojo.TblUser;
import cn.nines.blog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /*
      执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");

//        给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

//        添加资源的授权字符串
//        info.addStringPermission("user:add");

//        到数据库查询当前登录用户的授权字符串
//        获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        TblUser user = (TblUser) subject.getPrincipal();
//        User daUser = userService.findById(user.getId)
//        info.addStringPermission(dbUser.getPerms);

        return info;
    }

    /*
        执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        TblUser user = userService.findByUsername(token.getUsername());
//        编写shiro判断逻辑，判断账号和密码
//        1.判断账号
        if (user == null){
//            账号不存在
            return null;    //shiro底层会抛出UnKnwAccountException
        }
//        2.判断密码
        return new SimpleAuthenticationInfo(user, user.getPassword(),"");
    }
}
