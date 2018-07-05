// // 暂时注释掉，使用Mybatis的数据源
//
//package com.hz.learnkt.dao.jpa.config
//
//import com.alibaba.druid.pool.DruidDataSource
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.boot.context.properties.ConfigurationProperties
//import org.springframework.boot.jdbc.DataSourceBuilder
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.context.annotation.Primary
//import javax.sql.DataSource
//
///**
// * 自定义数据源配置类，声明两个数据源的Bean对象，以及使用application.yml配置文件内的前缀属性配置
// *
// * Created by hezhao on 2018-06-28 09:17
// */
//@Configuration
//class DataSourceConfig {
//
//    // 主
//    @Value("\${spring.datasource.master.jdbc-url}")
//    private lateinit var url: String
//
//    @Value("\${spring.datasource.master.username}")
//    private lateinit var user: String
//
//    @Value("\${spring.datasource.master.password}")
//    private lateinit var password: String
//
//    @Value("\${spring.datasource.master.driver-class-name}")
//    private lateinit var driverClass: String
//
//    // 从
//    @Value("\${spring.datasource.cluster.jdbc-url}")
//    private lateinit var url2: String
//
//    @Value("\${spring.datasource.cluster.username}")
//    private lateinit var user2: String
//
//    @Value("\${spring.datasource.cluster.password}")
//    private lateinit var password2: String
//
//    @Value("\${spring.datasource.cluster.driver-class-name}")
//    private lateinit var driverClass2: String
//
//
//    // 配置主数据源
//    @Bean(name = ["primaryDataSource"])
//    @Primary
//    @ConfigurationProperties(prefix="spring.datasource.master")
//    fun primaryDataSource(): DataSource {
//        // 创建默认数据源
////        return DataSourceBuilder.create().build()
//
//        // 创建Druid数据源
//        val dataSource = DruidDataSource()
//        dataSource.driverClassName = driverClass
//        dataSource.url = url
//        dataSource.username = user
//        dataSource.password = password
//        return dataSource
//    }
//
//    // 配置从数据源
//    @Bean(name = ["secondaryDataSource"])
//    @ConfigurationProperties(prefix="spring.datasource.cluster")
//    fun secondaryDataSource(): DataSource {
//        // 创建默认数据源
////        return DataSourceBuilder.create().build()
//
//        // 创建Druid数据源
//        val dataSource = DruidDataSource()
//        dataSource.driverClassName = driverClass2
//        dataSource.url = url2
//        dataSource.username = user2
//        dataSource.password = password2
//        return dataSource
//    }
//
//}