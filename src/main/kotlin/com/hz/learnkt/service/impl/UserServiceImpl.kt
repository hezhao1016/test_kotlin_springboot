package com.hz.learnkt.service.impl

import com.hz.learnkt.dao.UserRepository
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
    private lateinit var userRepository: UserRepository

    override fun queryUserList(): List<UserInfo> = userRepository.findAll()

    override fun queryUserById(id: Long): UserInfo? = userRepository.findById(id).orElse(null)

    override fun queryUserByUserName(userName: String): UserInfo? = userRepository.findByUserName(userName)

    override fun saveUser(userInfo: UserInfo): UserInfo = userRepository.save(userInfo)

    override fun deleteUser(id: Long) = userRepository.deleteById(id)

}