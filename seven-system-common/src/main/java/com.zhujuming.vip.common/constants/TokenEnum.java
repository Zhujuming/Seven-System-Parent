package com.zhujuming.vip.common.constants;

/**
 * Created by zx
 */
public enum TokenEnum {

    // todo change
    SIGNING_KEY("spring-security-@Jwt!&Kayle^#"),
    EXPENSE_CLIENT_SECRET("26DE6DBE37C84184409D676A633B814B51BFAE5C");
    private final String key;

    TokenEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
