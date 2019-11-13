package com.zhujuming.vip.common.exception;

public class HttpApiException extends GlobalException {

    public HttpApiException() { }

    public HttpApiException(String message) {
        super(400, message);
    }
}
