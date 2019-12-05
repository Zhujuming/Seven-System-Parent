package com.zhujuming.vip.listen;

import com.alibaba.fastjson.JSONObject;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @JmsListener(destination = "${activemq.queue}")
    public void receive(String msg) {
        System.out.println("监听器收到消息：" + msg);

        JSONObject parseObject = JSONObject.parseObject(msg);
        String type = (String) parseObject.get("type");
        String to = (String) parseObject.get("to");
        String content = (String) parseObject.get("content");
        if("email".equals(type)){
            System.out.println("发送邮件到："+to +"内容为："+content);
        }
    }
}
