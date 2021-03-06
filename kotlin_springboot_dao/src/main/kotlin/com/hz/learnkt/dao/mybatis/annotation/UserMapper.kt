package com.hz.learnkt.dao.annotation

import com.hz.learnkt.entity.master.UserInfo
import org.apache.commons.lang3.StringUtils
import org.apache.ibatis.annotations.*
import org.apache.ibatis.type.JdbcType
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.jdbc.SQL

/** Mybatis 使用注解实现DAO
 *
 * 由于使用了多数据源配置xml方式的，所以注解的暂时用不了。
 *
 * Created by hezhao on 2018-06-19 17:32
 */
@Mapper
interface UserMapper {

    // ----------------------- 脚本SQL --------------------------
    @Select("SELECT * FROM user_info")
    fun queryAll(): List<UserInfo>

    // 模糊查询，@Result 修饰返回的结果集
    @Select("""<script>
        SELECT id, name, user_name, password FROM user_info
        WHERE 1=1
        <if test="name != null and name != '' "> AND name like CONCAT('%',#{name},'%') </if>
        <if test="userName != null and userName != '' "> AND user_name = #{userName} </if>
        <if test="id != null"> AND id = #{id} </if>
        </script>""")
    @Results(
            Result(property = "id", column = "id", id = true, jdbcType = JdbcType.BIGINT, javaType = Long::class),
            Result(property = "userName", column = "user_name")
    )
    fun queryUserList(userInfo: UserInfo): List<UserInfo>

    @Select("SELECT * FROM user_info WHERE id = #{id}")
    @Results(
            Result(property = "userName", column = "user_name")
    )
    fun getOne(@Param("id") userId: Long): UserInfo

    @Insert("""
        INSERT INTO user_info (name, user_name, password)
            VALUES (#{name}, #{userName}, #{password})
        """)
    fun insertUser(userInfo: UserInfo): Int

    @Update("UPDATE user_info SET name=#{name} WHERE user_name =#{userName}")
    fun updateUser(userInfo: UserInfo): Int

    @Delete("DELETE FROM user_info WHERE id =#{id}")
    fun deleteUser(id: Long): Int


    // ----------------------- 在方法中构建SQL --------------------------
    // 使用UserInfoDaoProvider类的searchUser方法来生成sql
    // 增改删也有对应的@InsertProvider、@UpdateProvider、@DeleteProvider
    @SelectProvider(type = UserInfoDaoProvider::class, method = "searchUser")
    @Results(
            Result(property = "userName", column = "user_name")
    )
    fun searchUser(user: UserInfo): List<UserInfo>


    // ----------------------- 结构化SQL --------------------------
    @SelectProvider(type = UserInfoDaoProvider::class, method = "findUser")
    @Results(
            Result(property = "userName", column = "user_name")
    )
    fun findUser(user: UserInfo): List<UserInfo>


    /** 嵌套类 */
    class UserInfoDaoProvider {
        fun searchUser(user: UserInfo): String {
            var sql = StringBuilder()
            sql.append("SELECT * FROM user_info WHERE 1=1")
            if (user.id != null) {
                sql.append(" AND id = #{id}")
            }
            if (StringUtils.isNotBlank(user.name)) {
                sql.append(" AND name like CONCAT('%',#{name},'%')")
            }
            if (StringUtils.isNotBlank(user.userName)) {
                sql.append(" AND user_name = #{userName}")
            }
            sql.append(" ORDER BY id desc")
            return sql.toString()
        }

        // SELECT：表示要查询的字段，如果一行写不完，可以在第二行再写一个SELECT，这两个SELECT会智能的进行合并而不会重复
        // FROM和WHERE：跟SELECT一样，可以写多个参数，也可以在多行重复使用，最终会智能合并而不会报错
        // 这样语句适用于写很长的SQL时，能够保证SQL结构清楚。便于维护，可读性高。但是这种自动生成的SQL和HIBERNATE一样，在实现一些复杂语句的SQL时会束手无策。所以需要根据现实场景，来考虑使用哪一种动态SQL
        fun findUser(user: UserInfo): String {
            return object : SQL() {
                init {
                    SELECT("id,name")
                    SELECT("user_name,password")
                    FROM("user_info")
                    if (user.id != null) {
                        WHERE("id = #{id}")
                    }
                    if (StringUtils.isNotBlank(user.name)) {
                        WHERE("name like CONCAT('%',#{name},'%')")
                    }
                    if (StringUtils.isNotBlank(user.userName)) {
                        WHERE("user_name = #{userName}")
                    }
                    ORDER_BY("id desc", "name desc")
                }
            }.toString()
        }

        // 关于List传值 - 参考：https://blog.csdn.net/wangb_java/article/details/73657958
    }

}