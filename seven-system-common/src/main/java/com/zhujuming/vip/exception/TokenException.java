package com.zhujuming.vip.exception;

public class TokenException extends GlobalException {

    private static final long serialVersionUID = 1L;

    public TokenException(String message) {
        super(message);
    }

    public TokenException(int code, String message) {
        super(code, message);
    }
}
