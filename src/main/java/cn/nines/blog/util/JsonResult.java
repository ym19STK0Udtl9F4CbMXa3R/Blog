package cn.nines.blog.util;

import cn.nines.blog.exception.ExceptionEnum;

public class JsonResult {

    // 异常码
    private Integer code;

    // 异常信息
    private String message;

    public JsonResult() {
        this.code = 200;
        this.message = "SUCCESS";
    }

    public JsonResult(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getExceptionCode();
        this.message = exceptionEnum.getExceptionMsg();
    }

    public JsonResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
