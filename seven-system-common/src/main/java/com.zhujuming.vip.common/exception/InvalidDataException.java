package com.zhujuming.vip.common.exception;

/**
 * Created by zx
 */
public class InvalidDataException extends GlobalException {
    public InvalidDataException() {
    }

    public InvalidDataException(String message) {
        super(400, message);
    }
}
