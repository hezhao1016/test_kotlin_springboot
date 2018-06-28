package com.hz.learnkt.dao.jpa.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

import javax.persistence.EntityManager
import javax.sql.DataSource
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings

/**
 * 主数据源配置类
 *
 * Created by hezhao on 2018-06-28 09:17
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryPrimary",
        transactionManagerRef="transactionManagerPrimary",
        basePackages= ["com.hz.learnkt.dao.jpa.master"]) //设置Repository所在位置
class PrimaryDatSourceConfig {
    @Autowired
    @Qualifier("masterDataSource")
    private lateinit var primaryDataSource: DataSource

    @Primary
    @Bean(name = ["entityManagerPrimary"])
    fun entityManager(builder: EntityManagerFactoryBuilder): EntityManager? {
        return entityManagerFactoryPrimary(builder).`object`?.createEntityManager()
    }

    @Primary
    @Bean(name = ["entityManagerFactoryPrimary"])
    fun entityManagerFactoryPrimary(builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
        return builder
                .dataSource(primaryDataSource)
                .properties(getVendorProperties(primaryDataSource))
                .packages("com.hz.learnkt.entity.master") //设置实体类所在位置
                .persistenceUnit("primaryPersistenceUnit")
                .build()
    }

    @Autowired
    private lateinit var jpaProperties: JpaProperties

    /**
     * SpringBoot 2.0.x版本与1.5.x版本的主要区别在于getVerdorProperties这个方法，原来的getHibernateProperties是传参数DataSource，现在是传参数HibernateSettings
     * HibernateSettings类其实就是配置列名生成策略的，我们已经在yml里配置过了，这里直接new 一个空类过去就行了。
     */
    private fun getVendorProperties(dataSource: DataSource): MutableMap<String, Any>? {
        return jpaProperties.getHibernateProperties(HibernateSettings())
    }

    /**
     * 配置事务
     */
    @Primary
    @Bean(name = ["transactionManagerPrimary"])
    fun transactionManagerPrimary(builder: EntityManagerFactoryBuilder): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactoryPrimary(builder).`object`!!)
    }
}