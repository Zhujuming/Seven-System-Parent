package com.zhujuming.vip.handler;

import com.zhujuming.vip.model.TScheduledCronEntity;
import com.zhujuming.vip.mapper.task.TScheduledCronRepository;
import com.zhujuming.vip.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Component
public class HelloWorldTask extends BaseDynamicTimedTask {

    @Resource
    private TScheduledCronRepository repository;

    @Autowired
    private HelloService helloService;

    @Override
    Runnable task() {
        return new CheckHeart();
    }


    @Override
    String cron() {
        TScheduledCronEntity tScheduledCronEntity = repository.selectById(1l);
        return tScheduledCronEntity.getHelloScheduledCron();
    }

    class CheckHeart implements Runnable {
        @Override
        public void run() {
            helloService.updateTime();
            log.info("定时任务更新时间::: 心跳检查完毕！" + new Date());
        }
    }
}
