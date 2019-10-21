package cn.nines.blog.aspect;

import cn.nines.blog.pojo.SysView;
import cn.nines.blog.service.ViewService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Aspect
@Component
public class ViewAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ViewService viewService;

    // 切入点 任何修饰符 controller 下的所有类的所有参数的方法
    @Pointcut("execution(* cn.nines.blog.controller.HelloController.*(..))")
    public void view(){}

    @Before("view()")
    public void doBefore(JoinPoint joinPoint){
        // 获取 HttpServletRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取请求者的 ip地址
        String ip = request.getRemoteAddr();

        try {
            viewService.addView(new SysView(ip, new Date()));
        }catch (Exception e){
            logger.error(e.getMessage());
        }

    }

    @After("view()")
    public void doAfter(){
    }

}
