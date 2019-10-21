package cn.nines.blog.shiro;

import cn.nines.blog.shiro.realm.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/*
    shiro的配置类
 */
@Configuration
public class ShiroConfig{

    private static final transient Logger log = LoggerFactory.getLogger(ShiroConfig.class);

    /*
            创建ShiroFilterFactoryBean
         */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("DefaultWebSecurityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        设置Shiro内置过滤器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /*
            Shiro内置过滤器，可以实现权限相关的拦截器
                常用的过滤器：
                    anon：无需认证（登录）可以访问
                    authc：必须认证才可以访问
                    user：如果使用rememberMe的功能可以直接访问
                    perms：该资源必须得到资源权限才可以访问
                    role：该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
//        anon 设置放行     filterMap.put("/*", "authc"); 必须放最后 否则无效
        filterMap.put("/", "anon");
        filterMap.put("/admin/login", "anon");
        filterMap.put("/admin/types", "anon");
        filterMap.put("/admin/tags", "anon");
//        权限过滤器
//        注意：当权限拦截后，shiro会自动跳转到未授权页面
//        filterMap.put("/mustBeAuth","perms[user:add]");
//        匿名静态资源放行
        filterMap.put("/static/**", "anon");
        filterMap.put("/admin/**", "authc");  //拦截所有请求
        System.out.println(filterMap);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

//        设置拦截后跳转到的页面
        shiroFilterFactoryBean.setLoginUrl("/admin/login.html");
//        设置未授权提示页面
//        shiroFilterFactoryBean.setUnauthorizedUrl("/toNoAuth");

        return shiroFilterFactoryBean;
    }

    /*
        创建DefaultWebSecurityManager
     */
    @Bean(name = "DefaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("UserRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /*
        创建Realm
     */
    @Bean(name = "UserRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }


}
