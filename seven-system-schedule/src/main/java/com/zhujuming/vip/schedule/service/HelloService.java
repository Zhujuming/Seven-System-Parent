package com.zhujuming.vip.schedule.service;

import com.zhujuming.vip.common.vo.ResponseVO;

public interface HelloService {

    ResponseVO selectOneTest(Long staffId);

    ResponseVO selectByIdTest(Integer id);

    ResponseVO selectByMapTest(String phone,String chineseName);

    ResponseVO selectBatchIdsTest(Integer[] ids);

    ResponseVO selectPageTest(Integer currentPage, Integer size);

    ResponseVO selectListPageTest(Integer page, Integer pageSize);

    ResponseVO insertTest();

    ResponseVO deleteByIdTest(Integer id);

    ResponseVO deleteByMapTest(String loginName,Integer isDel);

    ResponseVO deleteBatchIdsTest(Integer[] ids);

    ResponseVO updateByIdTest(Long id);

    ResponseVO updateTime();

}
