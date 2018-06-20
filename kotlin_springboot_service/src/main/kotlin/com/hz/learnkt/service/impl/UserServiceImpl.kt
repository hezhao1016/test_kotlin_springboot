package com.hz.learnkt.service.impl

import com.hz.learnkt.dao.dao.UserRepository
import com.hz.learnkt.dao.entity.UserInfo
import com.hz.learnkt.entity.UserInfoDto
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

    override fun queryUserList(): List<UserInfoDto> = userDao.findAll()

    override fun queryUserById(id: Long): UserInfoDto? = userDao.findById(id).orElse(null)

    override fun queryUserByUserName(userName: String): UserInfoDto? = userDao.findByUserName(userName)

    override fun saveUser(userInfo: UserInfoDto): UserInfo = userDao.save(userInfo)

    override fun deleteUser(id: Long) = userDao.deleteById(id)

}