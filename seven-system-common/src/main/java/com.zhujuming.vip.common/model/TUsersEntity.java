package com.zhujuming.vip.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Seven Zhu
 * @since 2019-09-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_users")
public class TUsersEntity extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 权限
     */
    private String role;


}
