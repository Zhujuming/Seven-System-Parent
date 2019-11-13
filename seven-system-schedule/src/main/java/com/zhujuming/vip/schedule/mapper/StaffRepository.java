//package com.zhujuming.vip.schedule.mapper;
//
//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.zhujuming.vip.common.model.TAllStaffEntity;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//
///**
// * XML 自定义分页
// *  可以继承或者不继承BaseMapper
// */
//@Mapper
//public interface StaffRepository extends BaseMapper<TAllStaffEntity> {
//
//    /**
//     * 查询 : 根据state状态查询用户列表，分页显示
//     * 注意!!: 如果入参是有多个,需要加注解指定参数名才能在xml中取值
//     *
//     * @param page 分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一位(你可以继承Page实现自己的分页对象)
//     * @param state 状态
//     * @return 分页对象
//     */
//    IPage<TAllStaffEntity> selectPageVo(Page page, @Param("state") Integer state);
//
//    List<TAllStaffEntity> selectPageExt(Page<TAllStaffEntity> page, @Param("user") TAllStaffEntity user);
//
//    int deleteStaff();
//
//}
