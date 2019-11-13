package com.zhujuming.vip.common.exception;


import com.zhujuming.vip.common.constants.ResponseCode;

/**
 * @author dragonlai
 * @date 2017年12月12日 23:09:11
 */
public class ServiceException extends RuntimeException {

    private int code;

    public ServiceException(ResponseCode responseCode, Throwable cause) {
        super(responseCode.getDescription(), cause);
        this.code = responseCode.value();
    }

    public ServiceException(ResponseCode responseCode) {
        super(responseCode.getDescription());
        this.code = responseCode.value();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
