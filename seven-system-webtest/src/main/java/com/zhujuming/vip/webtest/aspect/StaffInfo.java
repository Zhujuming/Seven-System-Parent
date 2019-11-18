package com.zhujuming.vip.webtest.aspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StaffInfo extends TAllStaffEntity {

    /**
     * 用户开关
     */
    private CostUserFeatureTogglesResult userToggle;
}
