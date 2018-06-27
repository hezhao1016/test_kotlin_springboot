package com.hz.learnkt.dao.mybatis.xml.master

import com.hz.learnkt.entity.UserInfo
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/** 用户 DAO 接口类
 * Created by hezhao on 2018-06-27 17:27
 */
@Mapper
interface UserInfoDao {
    /**
     * 根据用户名获取用户信息
     *
     * @param userName
     * @return
     */
    fun findByUserName(@Param("userName") userName: String): UserInfo
}