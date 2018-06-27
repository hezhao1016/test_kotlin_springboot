package com.hz.learnkt.service.impl

import com.hz.learnkt.dao.dao.UserMapper
import com.hz.learnkt.dao.jpa.UserRepository
import com.hz.learnkt.entity.UserInfo
import com.hz.learnkt.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by hezhao on 2018-06-19 18:14
 */
@Service
class UserServiceImpl: UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var userMapper: UserMapper

    override fun queryUserList(): List<UserInfo> = userRepository.findAll()

    override fun queryUserById(id: Long): UserInfo? = userRepository.findById(id).orElse(null)

    override fun queryUserByUserName(userName: String): UserInfo? = userRepository.findByUserName(userName)

    override fun queryUserByNameNativeQuery(name: String): List<UserInfo> = userRepository.selectUserInfoByNameNativeQuery(name)

    override fun queryUserByName(name: String): List<UserInfo> = userRepository.selectUserInfoByName(name)

    override fun searchUserName(userName: String): List<UserInfo> = userRepository.searchUserName(userName)

    override fun saveUser(userInfo: UserInfo): UserInfo = userRepository.save(userInfo)

    override fun updateNameByUserName(userInfo: UserInfo): Int = userRepository.updateNameByUserName(userInfo.name ?: "", userInfo.userName ?: "")

    override fun deleteUser(id: Long) = userRepository.deleteById(id)

    override fun deleteUserByUserName(userName: String): Int = userRepository.deleteUserInfoByUserName(userName)

    override fun queryUserListByMybatis(userInfo: UserInfo): List<UserInfo> {
        return userMapper.queryUserList(userInfo)
    }

    override fun insertUserByMybatis(userInfo: UserInfo): Int {
        return userMapper.insertUser(userInfo)
    }

}