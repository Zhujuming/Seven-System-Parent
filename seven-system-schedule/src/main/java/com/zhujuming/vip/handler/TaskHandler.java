package com.zhujuming.vip.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Component
@EnableAsync //启用异步任务
@RestController
public class TaskHandler {

    @Autowired
    private HelloWorldTask helloWorldTask;

    @Async("threadPoolTaskExecutor")
    @RequestMapping("v1/startCron")
    public void scheduledStartCron() {
        helloWorldTask.startCron();
        log.info("##################################:" + Thread.currentThread().getName() + "--->" + Thread.currentThread().getId());
    }
    @Async("threadPoolTaskExecutor")
    @RequestMapping("v1/stopCron")
    public void scheduledStopCron() {
        helloWorldTask.stopCron();
        log.info("##################################:" + Thread.currentThread().getName() + "--->" + Thread.currentThread().getId());
    }
    @Async("threadPoolTaskExecutor")
    @RequestMapping("v1/changeCron")
    public void scheduledChangeCron() {
        helloWorldTask.changeCron();
        log.info("##################################:" + Thread.currentThread().getName() + "--->" + Thread.currentThread().getId());
    }

    //自定义线程池
    @Bean(name = "myThreadPoolTaskExecutor")
    public TaskExecutor getMyThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(20);
        taskExecutor.setMaxPoolSize(50);
        taskExecutor.setQueueCapacity(25);
        taskExecutor.setKeepAliveSeconds(200);
        taskExecutor.setThreadNamePrefix("Job-ThreadPool-");
        // 线程池对拒绝任务（无线程可用）的处理策略，目前只支持AbortPolicy、CallerRunsPolicy；默认为后者
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //调度器shutdown被调用时等待当前被调度的任务完成
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        //等待时长
        taskExecutor.setAwaitTerminationSeconds(60);
        taskExecutor.initialize();
        return taskExecutor;
    }
}
