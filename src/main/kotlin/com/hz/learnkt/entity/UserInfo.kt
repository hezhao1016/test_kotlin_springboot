package com.hz.learnkt.entity

import java.io.Serializable
import javax.persistence.*

/**
 * Created by hezhao on 2018-06-19 17:09
 */

@Entity
@Table(name = "user_info")
data class UserInfo(
        @Id
        @Column(name= "id", nullable = false)
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0L,

        @Column
        var name: String?,

        @Column(name= "username", nullable = false)
        var userName: String,

        @Column(nullable = false)
        var password: String

): Serializable {
        // 次构造函数
        protected constructor() : this(id = 0L, name = null, userName = "", password = "") {

        }
}