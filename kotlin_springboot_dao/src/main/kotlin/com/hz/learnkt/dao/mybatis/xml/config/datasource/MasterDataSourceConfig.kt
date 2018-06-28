package com.hz.learnkt.dao.mybatis.xml.config.datasource

import com.alibaba.druid.pool.DruidDataSource
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.jdbc.datasource.DataSourceTransactionManager

import javax.sql.DataSource

/** 主数据源配置信息
 *
 * Created by hezhao on 2018-06-27 17:14
 */
@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = [MasterDataSourceConfig.PACKAGE], sqlSessionFactoryRef = "masterSqlSessionFactory")
class MasterDataSourceConfig {
    companion object {
        // 精确到 master 目录，以便跟其他数据源隔离
        const val PACKAGE = "com.hz.learnkt.dao.mybatis.xml.master"
        const val MAPPER_LOCATION = "classpath:mapper/master/*.xml"
    }

    @Value("\${spring.datasource.master.url}")
    private lateinit var url: String

    @Value("\${spring.datasource.master.username}")
    private lateinit var user: String

    @Value("\${spring.datasource.master.password}")
    private lateinit var password: String

    @Value("\${spring.datasource.master.driver-class-name}")
    private lateinit var driverClass: String

    @Bean(name = ["masterDataSource"])
    @Primary
    fun masterDataSource(): DataSource {
        val dataSource = DruidDataSource()
        dataSource.driverClassName = driverClass
        dataSource.url = url
        dataSource.username = user
        dataSource.password = password
        return dataSource
    }

    @Bean(name = ["masterTransactionManager"])
    @Primary
    fun masterTransactionManager(): DataSourceTransactionManager {
        return DataSourceTransactionManager(masterDataSource())
    }

    @Bean(name = ["masterSqlSessionFactory"])
    @Primary
    @Throws(Exception::class)
    fun masterSqlSessionFactory(@Qualifier("masterDataSource") masterDataSource: DataSource): SqlSessionFactory? {
        val sessionFactory = SqlSessionFactoryBean()
        sessionFactory.setDataSource(masterDataSource)
        sessionFactory.setMapperLocations(PathMatchingResourcePatternResolver()
                .getResources(MasterDataSourceConfig.MAPPER_LOCATION))
        return sessionFactory.`object`
    }
}