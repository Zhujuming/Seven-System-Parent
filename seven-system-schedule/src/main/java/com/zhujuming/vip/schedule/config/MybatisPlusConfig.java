package com.zhujuming.vip.schedule.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class MybatisPlusConfig {

    //mybatis-plus SQL执行效率插件【生产环境可以关闭】
    @Bean
    @Profile({"dev"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    //mybatis-plus分页插件<br>
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page  = new PaginationInterceptor();
        page.setDialectType("mysql");
        // page.setLimit(你的最大单页限制数量，默认 500 条，小于 0 如 -1 不受限制);
        return page;
    }

    /**
     * 相当于顶部的：@MapperScan("com.zhujuming.vip.schedule.mapper*")
     * 这里可以扩展，比如使用配置文件来配置扫描Mapper的路径
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("com.zhujuming.vip.schedule.mapper*");
        return scannerConfigurer;
    }

}
