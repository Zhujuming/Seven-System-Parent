package com.zhujuming.vip.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MGateToken {
    private long timestamp;
    private String signature;
    private long userId;
    private String userName;
    private String password;
}
