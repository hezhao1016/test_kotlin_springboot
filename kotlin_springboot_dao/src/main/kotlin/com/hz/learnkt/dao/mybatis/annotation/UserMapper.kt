package com.hz.learnkt.dao.dao

import com.hz.learnkt.entity.UserInfo
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

/**
 * Created by hezhao on 2018-06-19 17:32
 */
@Mapper
interface UserMapper {

    @Select("""<script>
        select id, name, username, password from user_info
        where 1=1
        <if test='name != null'>AND name like #{name}%</if>
        <if test='userName != null'> AND username = #{userName}</if>
        <if test='id != null'> AND id = #{id}</if>
        </script>""")
    fun queryUserList(userInfo: UserInfo): List<UserInfo>

    @Insert("""
        insert into user_info (name, username, password)
            values (#{name}, #{userName}, #{password})
        """)
    fun insertUser(userInfo: UserInfo): Int


}