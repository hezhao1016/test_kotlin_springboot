package com.hz.learnkt.controller

import com.hz.learnkt.dao.UserRepository
import com.hz.learnkt.entity.UserInfo
import com.hz.learnkt.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/** 用户控制器
 *
 * 简书：
 * https://www.jianshu.com/p/9d5bf0e4943f
 * https://www.jianshu.com/p/bfeb80c858e0
 *
 * Created by hezhao on 2018-06-19 11:47
 */
@RestController
@RequestMapping("/users")
class UserController {

    // 必须使用延迟、var属性才能注入
    @Autowired
    private lateinit var userService: UserService

    @RequestMapping("/{id}", method = [RequestMethod.GET])
    fun getUserById(@PathVariable id: Long) = userService.queryUserById(id) ?: "没有记录"

    @RequestMapping("/name/{userName}", method = [RequestMethod.GET])
    fun getUserByUserName(@PathVariable userName: String) = userService.queryUserByUserName(userName) ?: "没有记录"

    @RequestMapping("/list", method = [RequestMethod.GET])
    fun getUserList(): List<UserInfo> {
        return userService.queryUserList()
    }
}