package com.zhujuming.vip.common.exception;

/**
 * service服务内部统一接口异常异常、远程接口统一抛出此异常
 *
 * @since 2019.06.20
 * 需要在面向内部服务的controller层中将所有的业务异常转化为InternalApiException
 */
public class InternalApiException extends RuntimeException {


    private static final long serialVersionUID = -4179539237744025098L;
    // 异常码
    private int code;

    // 异常信息(和responseVo字段msg名称一致,避免丢失异常信息)
    private String message;

    public InternalApiException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public InternalApiException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public InternalApiException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
