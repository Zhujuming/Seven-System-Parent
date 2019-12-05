package com.zhujuming.vip.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private String errorMsg;
    private int errorCode;

}
