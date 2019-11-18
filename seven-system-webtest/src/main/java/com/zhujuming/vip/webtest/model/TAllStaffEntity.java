package com.zhujuming.vip.webtest.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * <p>
 * 人员信息表
 * </p>
 *
 * @author dragonlai
 * @since 2019-04-09
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@TableName("t_all_staff")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TAllStaffEntity extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 人员id
     */
    private Long staffId;

    /**
     * 用户自定义id
     */
    private String customId;

    /**
     * 登录名称
     */
    private String loginName;

    /**
     * 英文名称
     * 英文名称
     */
    private String englishName;

    /**
     * 中文名称
     */
    private String chineseName;

    /**
     * 全名
     */
    private String fullName;

    /**
     * 性别
     */
    private String gender;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 组织id
     */
    private Integer bgId;

    /**
     * 组织编码（所属公司id)
     */
    private String bgCode;

    /**
     * 组织名称（所属公司名称）
     */
    private String bgName;

    /**
     * 组织编码
     */
    private String orgCode;

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 组织全路径
     */
    private byte[] orgPath;

    /**
     * 合同公司id
     */
    private String companyId;

    /**
     * 合同公司名称
     */
    private String companyName;

    /**
     * 工作地点id
     */
    private Integer workPlaceId;

    /**
     * 工作地点名称
     */
    private String workPlaceName;

    /**
     * 租户id
     */
    private Long tenantId;

    /**
     * 部门id
     */
    private Integer departmentId;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 组id
     */
    private Integer groupId;

    /**
     * 组名称
     */
    private String groupName;

    /**
     * 领导id
     */
    private Long leaderId;

    /**
     * 领导英文名
     */
    private String leaderEnglishname;

    /**
     * GMID
     */
    private Long gmId;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 官方id
     */
    private Integer officialId;

    /**
     * 官方名称
     */
    private String officialName;

    /**
     * 人员状态id
     */
    private Integer statusId;

    /**
     * 人员状态名称
     */
    private String statusName;

    /**
     * 人员类型id
     */
    private Integer typeId;

    /**
     * 人员类型名称
     */
    private String typeName;

    /**
     * 中心编码
     */
    private String centerCode;

    /**
     * 中心名称
     */
    private String centerName;

    /**
     * 工作中心id
     */
    private Integer workCenterId;

    /**
     * 工作中心名称
     */
    private String workCenterName;

    /**
     * 级别id
     */
    private Integer proLevelId;

    /**
     * 级别名称
     */
    private String proLevelName;

    /**
     * 是否启用
     */
    private Boolean isAvailable;

}
