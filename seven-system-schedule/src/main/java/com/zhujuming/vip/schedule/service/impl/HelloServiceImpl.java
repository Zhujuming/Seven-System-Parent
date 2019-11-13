//package com.zhujuming.vip.schedule.service.impl;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.zhujuming.vip.common.constants.ResponseCode;
//import com.zhujuming.vip.common.model.TAllStaffEntity;
//import com.zhujuming.vip.common.vo.ResponseVO;
//import com.zhujuming.vip.schedule.mapper.StaffRepository;
//import com.zhujuming.vip.schedule.service.HelloService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.*;
//
//@Slf4j
//@Service
//public class HelloServiceImpl implements HelloService {
//
//
//    @Autowired
//    private StaffRepository staffRepository;
//
//    /**
//     * selectOne():根据条件查询一条数据
//     * <p>
//     * 注：这个方法的sql语句就是where id = 1 and last_name = 更新测试，
//     * 若是符合这个条件的记录不止一条，那么就会报错。
//     */
//    @Override
//    public ResponseVO selectOneTest(Long staffId) {
//        try {
//
//            TAllStaffEntity tAllStaffEntity = new TAllStaffEntity();
//            tAllStaffEntity.setStaffId(staffId);
//            TAllStaffEntity tAllStaffEntity1 = staffRepository.selectOne(new QueryWrapper<>(tAllStaffEntity));
//            return new ResponseVO(ResponseCode.OK, tAllStaffEntity1);
//        } catch (Exception e) {
//            log.info("Error HelloServiceImpl.selectOneTest");
//            log.warn("Error msg:{}", e);
//            e.printStackTrace();
//            return new ResponseVO(ResponseCode.OPERATE_ERROR);
//        }
//    }
//
//    /**
//     * selectById():根据id查询：
//     */
//    @Override
//    public ResponseVO selectByIdTest(Integer id) {
//        try {
//            TAllStaffEntity tAllStaffEntity = staffRepository.selectById(id);
//            return new ResponseVO(ResponseCode.OK, tAllStaffEntity);
//        } catch (Exception e) {
//            log.info("Error HelloServiceImpl.selectByIdTest");
//            log.warn("Error msg:{}", e);
//            e.printStackTrace();
//            return new ResponseVO(ResponseCode.OPERATE_ERROR);
//        }
//    }
//
//    /**
//     * selectByMap():根据查询条件返回多条数据
//     * <p>
//     * 注：查询条件用map集合封装，columnMap，写的是数据表中的列名，而非实体类的属性名。
//     * 比如属性名为lastName，数据表中字段为last_name，这里应该写的是last_name。selectByMap方法返回值用list集合接收。
//     */
//    @Override
//    public ResponseVO selectByMapTest(String phone, String chineseName) {
//        try {
//            Map<String, Object> columnMap = new HashMap<>();
//            columnMap.put("phone", phone);//写表中的列名
//            columnMap.put("chinese_name", chineseName);
//            List<TAllStaffEntity> tAllStaffEntities = staffRepository.selectByMap(columnMap);
//            return new ResponseVO(ResponseCode.OK, tAllStaffEntities);
//        } catch (Exception e) {
//            log.info("Error HelloServiceImpl.selectByMapTest");
//            log.warn("Error msg:{}", e);
//            e.printStackTrace();
//            return new ResponseVO(ResponseCode.OPERATE_ERROR);
//        }
//    }
//
//    /**
//     * selectBatchIds():通过id批量查询
//     * <p>
//     * 注：把需要查询的id都add到list集合中，然后调用selectBatchIds方法，
//     * 传入该list集合即可，该方法返回的是对应id的所有记录，所有返回值也是用list接收。
//     */
//    @Override
//    public ResponseVO selectBatchIdsTest(Integer[] ids) {
//        try {
//            List<Integer> idList = new ArrayList<>();
//            // 访问数组，直接下标就可以了
//            for (int i = 0; i < ids.length; i++) {
//                idList.add(ids[i]);
//            }
//            List<TAllStaffEntity> tAllStaffEntities = staffRepository.selectBatchIds(idList);
//            return new ResponseVO(ResponseCode.OK, tAllStaffEntities);
//        } catch (Exception e) {
//            log.info("Error HelloServiceImpl.selectBatchIdsTest");
//            log.warn("Error msg:{}", e);
//            e.printStackTrace();
//            return new ResponseVO(ResponseCode.OPERATE_ERROR);
//        }
//    }
//
//    /**
//     * selectPage():分页查询
//     * <p>
//     * 注：selectPage方法就是分页查询，在page中传入分页信息，后者为null的分页条件，
//     * 这里先让其为null，讲了条件构造器再说其用法。这个分页其实并不是物理分页，而是内存分页。
//     * 也就是说，查询的时候并没有limit语句。等配置了分页插件后才可以实现真正的分页。
//     */
//    @Override
//    public ResponseVO selectPageTest(Integer currentPage, Integer size) {
//        try {
//            IPage<TAllStaffEntity> tAllStaffEntityIPage = staffRepository.selectPage(new Page<>(currentPage, size), null);
//            return new ResponseVO(ResponseCode.OK, tAllStaffEntityIPage);
//        } catch (Exception e) {
//            log.info("Error HelloServiceImpl.selectPageTest");
//            log.warn("Error msg:{}", e);
//            e.printStackTrace();
//            return new ResponseVO(ResponseCode.OPERATE_ERROR);
//        }
//    }
//
//    /**
//     * 使用插件实现分页查询
//     *
//     * @param page     页数
//     * @param pageSize 每页条数
//     * @return
//     */
//    @Override
//    public ResponseVO selectListPageTest(Integer page, Integer pageSize) {
//        try {
//            TAllStaffEntity tAllStaffEntity = new TAllStaffEntity();
//            Page<TAllStaffEntity> p = new Page<>(page, pageSize);
//            p.setRecords(staffRepository.selectPageExt(p, tAllStaffEntity));
//            List<TAllStaffEntity> records = p.getRecords();
//            return new ResponseVO(ResponseCode.OK, records);
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    /**
//     * 插入一条记录
//     *
//     * @return 插入成功记录数
//     */
//    @Override
//    public ResponseVO insertTest() {
//        try {
//            TAllStaffEntity entity = new TAllStaffEntity();
//            entity.setCreateBy("520");
//            entity.setCreateTime(new Date());
//            entity.setUpdateBy("520");
//            entity.setUpdateTime(new Date());
//            entity.setIsDel(0);
//            entity.setStaffId(1315l);
//            entity.setLoginName("seven");
//            entity.setEnglishName("seven");
//            entity.setChineseName("七");
//            entity.setFullName("seven(七)");
//            entity.setGender("男");
//            entity.setPhone("0");
//            entity.setBgId(0714);
//            entity.setBgName("爱玩事群");
//            entity.setOrgCode("777");
//            entity.setOrgName("爱玩科技有限公司");
//            entity.setWorkPlaceId(1);
//            entity.setWorkPlaceName("天堂总部");
//            entity.setTenantId(77777l);
//            int insert = staffRepository.insert(entity);
//            return new ResponseVO(ResponseCode.OK, insert);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseVO(ResponseCode.OPERATE_ERROR);
//        }
//    }
//
//    /**
//     * 根据 ID 删除
//     *
//     * @param id 主键ID
//     * @return 删除成功记录数
//     */
//    @Override
//    public ResponseVO deleteByIdTest(Integer id) {
//        try {
//            int num = staffRepository.deleteById(id);
//            return new ResponseVO(ResponseCode.OK, num);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseVO(ResponseCode.OPERATE_ERROR);
//        }
//    }
//
//    /**
//     * 根据 columnMap 条件，删除记录
//     *
//     * 注：该方法与selectByMap类似，将条件封装在columnMap中，然后调用deleteByMap方法，传入columnMap即可，返回值是Integer类型，表示影响的行数。
//     * @return 删除成功记录数
//     */
//    @Override
//    public ResponseVO deleteByMapTest(String loginName, java.lang.Integer isDel) {
//        try {
//            Map<String,Object> columnMap = new HashMap<>();
//            columnMap.put("login_name",loginName);
//            columnMap.put("is_del",isDel);
//            int num = staffRepository.deleteByMap(columnMap);
//            return new ResponseVO(ResponseCode.OK, num);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseVO(ResponseCode.OPERATE_ERROR);
//        }
//    }
//
//    /**
//     * 删除（根据ID 批量删除）
//     *
//     * 注：该方法和selectBatchIds类似，把需要删除的记录的id装进idList，然后调用deleteBatchIds，传入idList即可。
//     * @param ids 主键ID列表(不能为 null 以及 empty)
//     * @return 删除成功记录数
//     */
//    @Override
//    public ResponseVO deleteBatchIdsTest(Integer[] ids) {
//        try {
//            List<Integer> idList = new ArrayList<>();
//            // 访问数组，直接下标就可以了
//            for (int i = 0; i < ids.length; i++) {
//                idList.add(ids[i]);
//            }
//            int num = staffRepository.deleteBatchIds(idList);
//            return new ResponseVO(ResponseCode.OK, num);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseVO(ResponseCode.OPERATE_ERROR);
//        }
//    }
//
//
//    /**
//     * updateById()（根据id进行更新）
//     * 注：根据id进行更新，没有传值的属性就不会更新
//     * @param id 主键ID
//     * @return 删除更新记录数
//     */
//    @Override
//    public ResponseVO updateByIdTest(Long id) {
//        try {
//            TAllStaffEntity entity = new TAllStaffEntity();
//            entity.setId(id);
//            entity.setFullName("zhuzhu狭");
//            int num = staffRepository.updateById(entity);//
//            return new ResponseVO(ResponseCode.OK, num);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseVO(ResponseCode.OPERATE_ERROR);
//        }
//    }
//
//    @Override
//    @Transactional
//    public ResponseVO updateTime() {
//        try {
//            TAllStaffEntity entity = new TAllStaffEntity();
//            entity.setId(1l);
//            entity.setCreateTime(new Date());
//            entity.setUpdateTime(new Date());
//            int num = staffRepository.updateById(entity);
//            return new ResponseVO(ResponseCode.OK,num);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseVO(ResponseCode.OPERATE_ERROR);
//        }
//    }
//
//}
