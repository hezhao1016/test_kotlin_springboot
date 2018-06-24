package com.hz.learnkt.entity

import java.io.Serializable
import javax.persistence.*

/** 用户信息
 * Created by hezhao on 2018-06-19 17:09
 */
@Entity
@Table(name = "user_info")
// 命名查询
@NamedQuery(name = "UserInfo.searchUserName",query = "select u from UserInfo u where u.userName like :userName")
data class UserInfo(
        @Id
        @Column(name= "id", nullable = false)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0L,

        @Column
        var name: String? = null,

        @Column(name= "user_name", nullable = false)
        var userName: String = "",

        @Column(nullable = false)
        var password: String = "",

        // 一对多
        @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE], mappedBy = "user")
        var weibos: List<Weibo>

): Serializable