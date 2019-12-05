package com.zhujuming.vip.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhujuming.vip.model.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author dragonlai
 * @since 2019-07-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_ds_cost_authority")
public class TDsCostAuthorityEntity extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户信息
     */
    private String user;

    private Long staffId;

    /**
     * 是否海外，0-false-国内;1-true-海外
     */
    private Integer oversea;

    /**
     * 是否V7，0-false-不是V7;1-true-V7
     */
    private Integer v7;

    /**
     * 是否子公司，0-false-不是子公司员工;1-true-子公司员工
     */
    @TableField(value = "sub_company")
    private Integer subCompany;

    @TableField(value = "travel_plan")
    private Integer travelPlan;


}
