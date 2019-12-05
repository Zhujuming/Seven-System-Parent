package com.zhujuming.vip.server;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
public class SenderServer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void send (String msg){
        jmsMessagingTemplate.convertAndSend(queue,"测试消息队列："+msg);
    }
}
