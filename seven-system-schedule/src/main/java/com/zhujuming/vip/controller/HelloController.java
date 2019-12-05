package com.zhujuming.vip.controller;

import com.zhujuming.vip.vo.ResponseVO;
import com.zhujuming.vip.service.HelloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 例子
 * @Author: v_jumingzhu
 * @CreateDate: 2019/9/11$ 10:57$
 * @UpdateDate: 2019/9/11$ 10:57$
 * @UpdateRemark: Controller
 * @Version: 1.0
 */
@Slf4j
@RestController
@Api(description = "HelloController")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @PostMapping(value = "v1/selectOneTest")
    @ApiOperation(value = "测试-selectOneTest", httpMethod = "POST", notes = "测试-selectOneTest")
    public ResponseVO selectOneTest(Long staffId) {
        ResponseVO responseVO = helloService.selectOneTest(staffId);
        return responseVO;
    }

    @PostMapping(value = "v1/selectByIdTest")
    @ApiOperation(value = "测试-selectByIdTest", httpMethod = "POST", notes = "测试-selectByIdTest")
    public ResponseVO selectByIdTest(Integer id) {
        ResponseVO responseVO = helloService.selectByIdTest(id);
        return responseVO;
    }

    @ApiOperation(value = "测试-selectByMapTest", httpMethod = "POST", notes = "测试-selectByMapTest")
    @PostMapping(value = "v1/selectByMapTest")
    public ResponseVO selectByMapTest(String phone, String chineseName) {
        ResponseVO responseVO = helloService.selectByMapTest(phone, chineseName);
        return responseVO;
    }

    @ApiOperation(value = "测试-selectBatchIdsTest", httpMethod = "POST", notes = "测试-selectBatchIdsTest")
    @PostMapping(value = "v1/selectBatchIdsTest")
    public ResponseVO selectBatchIdsTest(Integer[] ids) {
        ResponseVO responseVO = helloService.selectBatchIdsTest(ids);
        return responseVO;
    }

    @ApiOperation(value = "测试-selectPageTest", httpMethod = "POST", notes = "测试-selectPageTest")
    @PostMapping(value = "v1/selectPageTest")
    public ResponseVO selectPageTest(Integer currentPage, Integer size) {
        ResponseVO responseVO = helloService.selectPageTest(currentPage,size);
        return responseVO;
    }

    @ApiOperation(value = "测试-selectListPageTest", httpMethod = "POST", notes = "测试-selectListPageTest")
    @PostMapping(value = "v1/selectListPageTest")
    public ResponseVO selectListPageTest(Integer page,Integer pageSize) {
        ResponseVO responseVO = helloService.selectListPageTest(page, pageSize);
        return responseVO;
    }
    @ApiOperation(value = "测试-insertTest", httpMethod = "POST", notes = "测试-insertTest")
    @PostMapping(value = "v1/insertTest")
    public ResponseVO insertTest() {
        ResponseVO responseVO = helloService.insertTest();
        return responseVO;
    }

    @ApiOperation(value = "测试-deleteByIdTest", httpMethod = "POST", notes = "测试-deleteByIdTest")
    @PostMapping(value = "v1/deleteByIdTest")
    public ResponseVO deleteByIdTest(Integer id) {
        ResponseVO responseVO = helloService.deleteByIdTest(id);
        return responseVO;
    }

    @ApiOperation(value = "测试-deleteByMapTest", httpMethod = "POST", notes = "测试-deleteByMapTest")
    @PostMapping(value = "v1/deleteByMapTest")
    public ResponseVO deleteByMapTest(String loginName,Integer isDel) {
        ResponseVO responseVO = helloService.deleteByMapTest(loginName,isDel);
        return responseVO;
    }

    @ApiOperation(value = "测试-deleteBatchIdsTest", httpMethod = "POST", notes = "测试-deleteBatchIdsTest")
    @PostMapping(value = "v1/deleteBatchIdsTest")
    public ResponseVO deleteBatchIdsTest(Integer[] ids) {
        ResponseVO responseVO = helloService.deleteBatchIdsTest(ids);
        return responseVO;
    }

    @ApiOperation(value = "测试-updateByIdTest", httpMethod = "POST", notes = "测试-updateByIdTest")
    @PostMapping(value = "v1/updateByIdTest")
    public ResponseVO updateByIdTest(Long id) {
        ResponseVO responseVO = helloService.updateByIdTest(id);
        return responseVO;
    }


}
