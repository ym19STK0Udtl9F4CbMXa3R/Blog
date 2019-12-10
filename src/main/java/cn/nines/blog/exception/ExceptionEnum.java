package cn.nines.blog.exception;

public enum  ExceptionEnum {
    // 系统发生异常，请联系管理员
    SYSTEM_ERROR(500, "系统发生异常，请联系管理员"),
    // 缺少必要的请求参数
    MISS_REQUEST_PARAMETER(400, "缺少必要的请求参数"),
    MISS_PathVariable(400, "缺失路径变量的名称"),
    INSERT_EXECUTION_FAILED(1001,"添加失败"),
    DATA_EXIST(1005,"添加失败,数据已存在"),
    PUBLISH_FAILED(1006,"发布失败"),
    DELECT_EXECUTION_FAILED(1002,"删除失败"),
    UPDATE_EXECUTION_FAILED(1003,"更新失败"),
    RECOVER_EXECUTION_FAILED(1004,"恢复失败"),
    DATE_EXCEPTION(1000,"数据异常"),
    NAME_EXCEEDED_CHARRACTER_LIMIT(2001, "姓名超过了限制，最大4个字符!"),
    ID_CANT_BE_ZERO(2002, "id 不能为0"),
    USERNAME_NOT_EXIST(3001,"username 不存在"),
    PASSWORD_NOT_MATCHING(3002,"password 不匹配"),
    ACCOUNT_IS_LOCKED(3003,"账号锁住了，不能登入"),
    ACCOUNT_IS_FROZEN(3004,"账号已冻结"),
    PLEASE_TRY_AGAIN(3005, "请重试"),
    UPLOAD_FAILED(4000, "上传失败");

    private Integer exceptionCode;

    private String exceptionMsg;

    ExceptionEnum(Integer exceptionCode, String exceptionMsg) {
        this.exceptionCode = exceptionCode;
        this.exceptionMsg = exceptionMsg;
    }

    public Integer getExceptionCode() {
        return exceptionCode;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

}
