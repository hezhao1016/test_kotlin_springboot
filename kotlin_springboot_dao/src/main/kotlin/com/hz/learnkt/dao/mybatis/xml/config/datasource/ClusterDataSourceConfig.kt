package com.hz.learnkt.dao.mybatis.xml.config.datasource

import com.alibaba.druid.pool.DruidDataSource
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.jdbc.datasource.DataSourceTransactionManager

import javax.sql.DataSource

/** 从数据源配置信息
 *
 * Created by hezhao on 2018-06-27 17:14
 */
@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = [ClusterDataSourceConfig.PACKAGE], sqlSessionFactoryRef = "clusterSqlSessionFactory")
class ClusterDataSourceConfig {
    companion object {
        // 精确到 cluster 目录，以便跟其他数据源隔离
        const val PACKAGE = "com.hz.learnkt.dao.mybatis.xml.cluster"
        const val MAPPER_LOCATION = "classpath:mapper/cluster/*.xml"
    }

    @Value("\${spring.datasource.cluster.jdbc-url}")
    private lateinit var url: String

    @Value("\${spring.datasource.cluster.username}")
    private lateinit var user: String

    @Value("\${spring.datasource.cluster.password}")
    private lateinit var password: String

    @Value("\${spring.datasource.cluster.driver-class-name}")
    private lateinit var driverClass: String

    @Bean(name = ["clusterDataSource"])
    fun clusterDataSource(): DataSource {
        val dataSource = DruidDataSource()
        dataSource.driverClassName = driverClass
        dataSource.url = url
        dataSource.username = user
        dataSource.password = password
        return dataSource
    }

    @Bean(name = ["clusterTransactionManager"])
    fun clusterTransactionManager(): DataSourceTransactionManager {
        return DataSourceTransactionManager(clusterDataSource())
    }

    @Bean(name = ["clusterSqlSessionFactory"])
    @Throws(Exception::class)
    fun clusterSqlSessionFactory(@Qualifier("clusterDataSource") clusterDataSource: DataSource): SqlSessionFactory? {
        val sessionFactory = SqlSessionFactoryBean()
        sessionFactory.setDataSource(clusterDataSource)
        sessionFactory.setMapperLocations(PathMatchingResourcePatternResolver()
                .getResources(ClusterDataSourceConfig.MAPPER_LOCATION))
        return sessionFactory.`object`
    }
}