package com.hz.learnkt.web.controller

import com.hz.learnkt.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

/** 测试thymeleaf模板引擎
 *
 * Created by hezhao on 2018-06-26 15:32
 */
@Controller
@RequestMapping("/page")
class PageController {

    @Autowired
    private lateinit var userService: UserService

    // 从application.properties中读取配置，如取不到默认值为tom
    @Value("\${myapp.name:tom}")
    private lateinit var name: String

    /**
     * 首页
     */
    @GetMapping(value = ["", "/index"])
    fun index(): String {
        return "system/index"
    }


    /**
     * 重定向
     */
    @RequestMapping("/redirect")
    fun redirect(): String {
        return "redirect:/page/list"
    }


    /**
     * 视图
     * @param model
     */
    @RequestMapping("/model")
    fun hello(model: ModelMap): String {
        model["name"] = name
        return "hello"
    }

    /**
     * 数据列表
     */
    @RequestMapping("/list")
    fun listUser(model: ModelMap): String {
        // 假数据
//        val userList = mutableListOf<Map<String, Any>>()
//        for (i in 0..9) {
//            userList.add(mapOf(
//                    "id" to i,
//                    "name" to "张三$i",
//                    "age" to 20 + i,
//                    "address" to "中国广州"
//            ))
//        }

        // 调用service
        val userList = userService.queryUserList()

        model["users"] = userList
        return "/user/list"
    }

}