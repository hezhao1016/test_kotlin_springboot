package com.hz.learnkt.web.controller.api

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
@RequestMapping("/api/users")
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

    @GetMapping("/username/{userName}")
    fun getUserByUserName(@PathVariable userName: String): Any {
        // return userService.queryUserByUserName(userName) ?: "没有记录"

        return userService.searchUserName(userName)
    }

    @GetMapping("/name/{name}")
    fun getUserByName(@PathVariable name: String):List<UserInfo> {
        // userService.queryUserByNameNativeQuery(name)

        return userService.queryUserByName(name)
    }

    @RequestMapping("/save")
    fun saveUser(userInfo: UserInfo) = userService.saveUser(userInfo)

    @RequestMapping("/update")
    fun updateNameByUserName(userInfo: UserInfo) = userService.updateNameByUserName(userInfo)

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

    @RequestMapping("/deleteByUserName/{userName}")
    fun deleteUserByUserName(@PathVariable userName: String) = userService.deleteUserByUserName(userName)


    @GetMapping("/list2")
    fun queryUserListByMybatis(userInfo: UserInfo): List<UserInfo> {
        return userService.queryUserListByMybatis(userInfo)
    }

    @GetMapping("/insert")
    fun insertUserByMybatis(userInfo: UserInfo) = userService.insertUserByMybatis(userInfo)
}

// http://127.0.0.1:8082/api/users/list
// http://127.0.0.1:8082/api/users/1
// http://127.0.0.1:8082/api/users/username/admin
// http://127.0.0.1:8082/api/users/name/ja
// http://127.0.0.1:8082/api/users/save?name=bob&userName=bob123&password=12345
// http://127.0.0.1:8082/api/users/save?id=1&name=jacknew&userName=admin&password=12345
// http://127.0.0.1:8082/api/users/update?id=1&name=jack&userName=admin
// http://127.0.0.1:8082/api/users/delete/2
// http://127.0.0.1:8082/api/users/deleteByUserName/bob123

// http://127.0.0.1:8082/api/users/list2?name=ja&userName=admin&id=1
// http://127.0.0.1:8082/api/users/insert?name=tom&userName=tom123&password=1234