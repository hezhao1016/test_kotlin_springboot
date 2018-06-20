package com.hz.learnkt.service

import com.hz.learnkt.entity.UserInfoDto

/**
 * Created by hezhao on 2018-06-19 18:14
 */
interface UserService {
    fun queryUserList(): List<UserInfoDto>

    fun queryUserById(id: Long): UserInfoDto?

    fun queryUserByUserName(userName: String): UserInfoDto?

    fun saveUser(userInfo: UserInfoDto): UserInfoDto

    fun deleteUser(id: Long)
}