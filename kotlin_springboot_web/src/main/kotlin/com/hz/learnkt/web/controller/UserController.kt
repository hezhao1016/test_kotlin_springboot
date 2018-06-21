package com.hz.learnkt.web.controller

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.hz.learnkt.entity.UserInfo
import com.hz.learnkt.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/** 用户控制器
 *
 * Created by hezhao on 2018-06-19 11:47
 */
@RestController
@RequestMapping("/users")
class UserController {

    // 必须使用延迟、var属性才能注入
    @Autowired
    private lateinit var userService: UserService

    @RequestMapping("/list", method = [RequestMethod.GET])
    fun getUserList(): List<UserInfo> {
        return userService.queryUserList()
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long) = userService.queryUserById(id) ?: "没有记录"

    @RequestMapping("/name/{userName}", method = [RequestMethod.GET])
    fun getUserByUserName(@PathVariable userName: String) = userService.queryUserByUserName(userName) ?: "没有记录"

    @RequestMapping("/save")
    fun saveUser(userInfo: UserInfo) = userService.saveUser(userInfo)

    @RequestMapping("/delete/{id}")
    fun deleteUser(@PathVariable id: Long): JSON {
        val json:JSONObject = JSONObject()

        val userInfo = userService.queryUserById(id)

        if (userInfo == null) {
            json["msg"] = "删除失败，用户不存在"
            json["code"] = "5000"
            return json
        }

        userService.deleteUser(id)
        json["msg"] = "删除成功"
        json["code"] = "0000"
        return json
    }
}

// http://127.0.0.1:8082/api/users/list
// http://127.0.0.1:8082/api/users/1
// http://127.0.0.1:8082/api/users/name/admin
// http://127.0.0.1:8082/api/users/save?name=bob&userName=bob123&password=12345
// http://127.0.0.1:8082/api/users/save?id=1&name=jacknew&userName=admin&password=123456
// http://127.0.0.1:8082/api/users/delete/2