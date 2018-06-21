package com.hz.learnkt.dao.jpa

import com.hz.learnkt.entity.UserInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

/**
 * Created by hezhao on 2018-06-19 17:32
 */
interface UserRepository : JpaRepository<UserInfo, Long>{
    /**
     * 根据username查询UserInfo，不需要写实现, JPA根据自定义查询时的命名规则去实现
     */
    fun findByUserName(username: String): UserInfo?

    // 原生SQL查询
    @Query(value = "SELECT * FROM user_info WHERE name like %?1%", nativeQuery = true)
    fun getUserInfoByName(name: String): List<UserInfo>


}