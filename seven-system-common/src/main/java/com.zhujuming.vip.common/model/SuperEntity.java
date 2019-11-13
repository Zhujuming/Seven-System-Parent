package com.zhujuming.vip.common.model;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类
 *
 * @author dragonlai
 */
@Data
public class SuperEntity<T extends Model> extends Model<T> {

    /**
     * 主键ID
     */
    @TableId(type = IdType.ID_WORKER)
    private Long id;

    /**
     * 备注
     */
    @TableField(value = "memo")
    private String memo;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date updateTime;

    /**
     * 是否删除（0：未删除；1：已删除）
     */
    @TableField(value = "is_del")
    private Integer isDel;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
