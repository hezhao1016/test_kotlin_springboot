package com.hz.learnkt

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.servlet.view.json.MappingJackson2JsonView

/**
 * Spring Boot 应用启动类
 *
 * Spring Boot 部署到 外部Tomcat 中去启动
 * 注意：启动类需要继承自SpringBootServletInitializer，然后实现其configure()方法.
 *
 * 同时，也可以使用spring-boot:run和war两种方式启动
 * 但是，无法在使用main方法直接运行了
 *
 * 鉴于开发阶段：暂时注释掉这个功能。
 *
 * Created by hezhao on 2018-06-24 15:32
 */

// Spring Boot 应用的标识
// 一般将Application放在最外层包，也就是要包含所有子包。 Spring Boot 会自动加载启动类所在包下及其子包下的所有组件.
// 也可以手动设置扫描包的路径，可设置多个路径，以下两种方式都可以：
@SpringBootApplication(scanBasePackages = ["com.hz.learnkt"])
// @ComponentScan("com.hz.learnkt")
class WebApplication /*: SpringBootServletInitializer()*/ {

    /*override fun configure(builder: SpringApplicationBuilder): SpringApplicationBuilder {
        return builder.sources(WebApplication::class.java)
    }*/

    /** 在Spring中初始化Jackson */
    @Bean
    fun json(): MappingJackson2JsonView {
        return MappingJackson2JsonView(ObjectMapper())
    }
}

/** 运行Spring Boot */
// 程序启动入口
// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
fun main(args: Array<String>) {
    // 类似Java的启动方式
    // SpringApplication.run(com.hz.learnkt.Application::class.java, *args)

    // Kotlin 特有的简便写法
    runApplication<WebApplication>(*args)
}