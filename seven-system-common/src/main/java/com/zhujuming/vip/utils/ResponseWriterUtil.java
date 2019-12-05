package com.zhujuming.vip.utils;

import com.zhujuming.vip.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class ResponseWriterUtil {

    public static void writer(HttpServletResponse response, ResponseVO responseVO) {
        PrintWriter writer = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            writer = response.getWriter();
            writer.write(Serializer.serialize(responseVO));
            writer.flush();
        } catch (IOException e) {
            log.warn("writer error.", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

}
