package com.hz.learnkt.web.controller.api

import com.hz.learnkt.service.CityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/** 城市信息控制器
 *
 * Created by hezhao on 2018-06-27 17:53
 */
@RestController
@RequestMapping("/api/city")
class CityController {

    // 必须使用延迟、var属性才能注入
    @Autowired
    private lateinit var cityService: CityService

    @GetMapping(value = ["", "/list"])
    fun list() = cityService.findAll()
}

// http://127.0.0.1:8082/api/city/