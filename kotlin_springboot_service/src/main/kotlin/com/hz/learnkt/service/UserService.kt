package com.hz.learnkt.service

import com.hz.learnkt.entity.UserInfo

/**
 * Created by hezhao on 2018-06-19 18:14
 */
interface UserService {
    fun queryUserList(): List<UserInfo>

    fun queryUserById(id: Long): UserInfo?

    fun queryUserByUserName(userName: String): UserInfo?

    fun queryUserByName(name: String): List<UserInfo>

    fun saveUser(userInfo: UserInfo): UserInfo

    fun deleteUser(id: Long)
}