package com.zhujuming.vip.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

@Configuration
public class QueueConfig {

    @Value("${activemq.queue}")
    private String queue;

    @Bean
    public Queue myQueue(){
        return new ActiveMQQueue(queue);
    }

}
