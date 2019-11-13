package com.zhujuming.vip.schedule.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.ScheduledFuture;


@Slf4j
public abstract class BaseDynamicTimedTask {

    //接受任务的返回结果
    private ScheduledFuture<?> future;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    //实例化一个线程池任务调度类,可以使用自定义的ThreadPoolTaskScheduler
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        return executor;
    }

    /**
     * 启动定时任务
     *
     * @return
     */
    public boolean startCron() {
        boolean flag = false;
        future = threadPoolTaskScheduler.schedule(task(), new CronTrigger(cron()));
        if (future != null) {
            flag = true;
            log.info("start Task success!");
        } else {
            log.info("start Task failed !");
        }
        return flag;
    }

    public boolean changeCron() {
        boolean flag = stopCron();// 先停止，在开启.
        if (!flag) {
            log.info("changeCron stop Task failed !");
        }
        future = threadPoolTaskScheduler.schedule(task(), new CronTrigger(cron()));
        if (future != null) {
            flag = true;
            log.info("changeCron start Task success!");
        } else {
            log.info(" changeCron start Task failed !");
        }
        return flag;
    }

    /**
     * 停止定时任务
     *
     * @return
     */
    public boolean stopCron() {
        boolean flag = false;
        if (future != null) {
            boolean cancel = future.cancel(true);
            if (cancel) {
                flag = true;
                log.info("stop Task success!");
            } else {
                log.info("stop Task fail !");
            }
        } else {
            flag = true;
            log.info(" Task has stopped ！");
        }
        return flag;
    }

    abstract Runnable task();

    abstract String cron();
}
