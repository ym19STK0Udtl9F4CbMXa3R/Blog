package cn.nines.blog.handler;

import cn.nines.blog.exception.ExceptionEnum;
import cn.nines.blog.util.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult handleHttpMessageNotReadableException(HttpServletRequest request, MissingServletRequestParameterException e){
        logger.error("Request : {}, Exception : {}", request.getRequestURL(), e.getMessage());
        return new JsonResult(ExceptionEnum.MISS_REQUEST_PARAMETER);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public JsonResult handleMissingPathVariableException(HttpServletRequest request, MissingPathVariableException e){
        logger.error("Request : {}, Exception : {}", request.getRequestURL(), e.getMessage());
        return new JsonResult(ExceptionEnum.MISS_PathVariable);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleUnexpectedServer(HttpServletRequest request, Exception e){
        logger.error("Request : {}, Exception : {}", request.getRequestURL(), e.getMessage());
        return new JsonResult(ExceptionEnum.SYSTEM_ERROR);
    }

}
