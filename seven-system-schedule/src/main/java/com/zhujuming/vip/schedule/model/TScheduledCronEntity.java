package com.zhujuming.vip.schedule.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhujuming.vip.common.model.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_scheduled_cron")
public class TScheduledCronEntity extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 时间表达式
     */
    private String helloScheduledCron;

}
