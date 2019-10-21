package cn.nines.blog.exception;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CommonErrorPageRegistrar implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        // HttpStatus.NOT_FOUND 对应的响应码是404
        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.html");
        // HttpStatus.INTERNAL_SERVER_ERROR 对应的响应码是500
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.html");

//        ErrorPage errorPage400 = new ErrorPage(HttpStatus.BAD_REQUEST, "/error/error.html");

        // 根据ErrorPage 的构造函数不仅可以传入Http 对应状态码的枚举，还可以传入对应异常的class 对象
        // ErrorPage nullPointException = new ErrorPage(NullPointerException.class, "/对应的提示页面");

        registry.addErrorPages(errorPage404, errorPage500);
    }
}
