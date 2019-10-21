package cn.nines.blog.aspect;

import cn.nines.blog.pojo.SysLog;
import cn.nines.blog.service.LogService;
import cn.nines.blog.util.BrowserUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
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
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private LogService logService;

    // 切入点 任何修饰符 controller 下的所有类的所有参数的方法
    @Pointcut("execution(* cn.nines.blog.controller.*.*(..))")
    public void log(){}

    @Pointcut("execution(* cn.nines.blog.controller.IndexController.findBlogParticulars(..))")
    public void blogLog(){}

    @Before("blogLog()")
    public void doBlogBefore(JoinPoint joinPoint){
        // 获取 HttpServletRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取请求的url
        String url = request.getRequestURL().toString();
        // 获取浏览器信息
        String browserInfo = BrowserUtil.getOsAndBrowserInfo(request);
        // 获取请求者的 ip地址
        String ip = request.getRemoteAddr();
        // 获取请求的 方法
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();

        try {
            logService.addLog(new SysLog(ip, new Date(), classMethod, url, browserInfo));
        }catch (Exception e){
            logger.error(e.getMessage());
        }

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        // 获取 HttpServletRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取请求的url
        String url = request.getRequestURL().toString();
        // 获取请求者的 ip地址
        String ip = request.getRemoteAddr();
        // 获取请求的 方法
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        // 获取请求的参数
        Object[] args = joinPoint.getArgs();

        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);

        logger.info("Request : {}", requestLog);
//        System.out.println("-----Before------");
    }

    @After("log()")
    public void doAfter(){
//        System.out.println("-------After------");
    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result){
        // 获取返回值
        logger.info("Result : {}", result);
    }

}
