package com.hz.learnkt.web

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.view.json.MappingJackson2JsonView

@SpringBootApplication
class WebApplication {
    /** 在Spring中初始化Jackson */
    @Bean
    fun json(): MappingJackson2JsonView {
        return MappingJackson2JsonView(ObjectMapper())
    }
}

/** 运行Spring Boot */
fun main(args: Array<String>) {
    // 类似Java的启动方式
    // SpringApplication.run(com.hz.learnkt.web.WebApplication::class.java, *args)

    // Kotlin 特有的简便写法
    runApplication<WebApplication>(*args)
}