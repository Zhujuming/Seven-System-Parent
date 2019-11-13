package com.zhujuming.vip.common.exception;


import com.zhujuming.vip.common.vo.ResponseVO;

public class ExpenseException extends RuntimeException {

    private int code = 400;
    private String message;
    private Throwable e;
    private ResponseVO result;

    public ExpenseException() {
    }

    public ExpenseException(String message) {
        super(message);
        this.message = message;
    }

    public ExpenseException(String message, Throwable ex) {
        super(message);
        this.message = message;
        this.e = ex;
    }

    public ExpenseException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ExpenseException(int code, String message, Throwable ex) {
        super(message);
        this.code = code;
        this.message = message;
        this.e = ex;
    }

    public ExpenseException(ResponseVO result) {
        super(result.getMsg());
        this.code = result.getCode();
        this.message = result.getMsg();
        this.result = result;
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

    public Throwable getE() {
        return e;
    }

    public void setE(Throwable e) {
        this.e = e;
    }

    public ResponseVO getResult() {
        return result;
    }

    public void setResult(ResponseVO result) {
        this.result = result;
    }
}
