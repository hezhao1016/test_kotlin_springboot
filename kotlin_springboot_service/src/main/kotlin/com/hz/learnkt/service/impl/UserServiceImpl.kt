package com.hz.learnkt.service.impl

import com.hz.learnkt.dao.jpa.UserRepository
import com.hz.learnkt.entity.UserInfo
import com.hz.learnkt.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by hezhao on 2018-06-19 18:14
 */
@Service
class UserServiceImpl: UserService {

    @Autowired
    private lateinit var userDao: UserRepository

    override fun queryUserList(): List<UserInfo> = userDao.findAll()

    override fun queryUserById(id: Long): UserInfo? = userDao.findById(id).orElse(null)

    override fun queryUserByUserName(userName: String): UserInfo? = userDao.findByUserName(userName)

    override fun queryUserByName(name: String?): List<UserInfo> {
        userDao.getUserInfoByName()
    }

    override fun saveUser(userInfo: UserInfo): UserInfo = userDao.save(userInfo)

    override fun deleteUser(id: Long) = userDao.deleteById(id)

}