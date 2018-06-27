package com.hz.learnkt

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.servlet.view.json.MappingJackson2JsonView

/** Spring Boot 应用启动类
 *
 * Created by hezhao on 2018-06-24 15:32
 */

// Spring Boot 应用的标识
// 一般将Application放在最外层包，也就是要包含所有子包。 Spring Boot 会自动加载启动类所在包下及其子包下的所有组件.
// 也可以手动设置扫描包的路径，可设置多个路径，以下两种方式都可以：
@SpringBootApplication(scanBasePackages = ["com.hz.learnkt"])
// @ComponentScan("com.hz.learnkt")
class WebApplication {
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