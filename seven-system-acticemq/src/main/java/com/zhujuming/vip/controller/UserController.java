package com.zhujuming.vip.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhujuming.vip.server.SenderServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SenderServer senderServer;

    @RequestMapping("/register")
    public String register(String name){
        long startTime = System.currentTimeMillis();
        try {
            //senderServer.send("发送短信到 ：13902434787");
            JSONObject json  = new JSONObject();
            json.put("type","email");
            json.put("to","893371542@qq.com");
            json.put("content","恭喜你注册成功！"+name);
            senderServer.send(json.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
            return "处理异常";
        }
        long endTime = System.currentTimeMillis();
        return  name+"用户，注册成功，耗时"+(endTime-startTime);
    }
    //测试地址：http://127.0.0.1:9002/user/register?name=zhujuming
}
