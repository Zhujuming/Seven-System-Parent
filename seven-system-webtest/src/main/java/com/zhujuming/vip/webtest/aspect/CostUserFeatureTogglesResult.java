package com.zhujuming.vip.webtest.aspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostUserFeatureTogglesResult implements Serializable {

    private long UserId;
    private boolean Oversea;
    private boolean V7;
    private boolean SubCompany;

}
