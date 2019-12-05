package com.zhujuming.vip.utils;

import com.zhujuming.vip.exception.InvalidDataException;
import com.zhujuming.vip.vo.ParameterMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpMapUtil {

    private Map<String, Object> httpMap;
    private Map<String, Object> paging;
    private List<ParameterMap> parameterMapList;

    public HttpMapUtil initHttpMap(String appId, String batchId) {
        if (httpMap == null) {
            httpMap = new HashMap<>();
        }
        httpMap.put("AppId", appId);
        httpMap.put("BatchId", batchId);
        return this;
    }

    public HttpMapUtil setRequestMap(String className, String methodName) {
        if (httpMap == null) {
            httpMap = new HashMap<>();
        }
        Map<String, Object> methodParameter = new HashMap<>();
        if (paging != null) {
            methodParameter.put("paging", paging);
        }
        if (CollectionUtils.isNotEmpty(parameterMapList)) {
            for (ParameterMap parameterMap : parameterMapList) {
                methodParameter.put(parameterMap.getKey(), parameterMap.getValue());
            }
        }
        Map<String, Object> request = new HashMap<>();
        request.put("ClassName", className);
        request.put("MethodName", methodName);
        request.put("MethodParameter", methodParameter);
        httpMap.put("Request", Serializer.serialize(request));
        return this;
    }

    public HttpMapUtil setParameterMap(String key, Object value) {
        if (parameterMapList == null) {
            parameterMapList = new ArrayList<>();
        }
        parameterMapList.add(new ParameterMap(key, value));
        return this;
    }

    public void setPaging(int index, int size) {
        if (paging == null) {
            paging = new HashMap<>();
        }
        if (index <= 0 || size <= 0) {
            index = 1;
            size = 10;
        }
        paging.put("Index", index);
        paging.put("Size", size);
    }

    public String serializeMap() {
        if (httpMap == null) {
            throw new InvalidDataException("请求参数错误");
        }
        return Serializer.serialize(httpMap);
    }
}
