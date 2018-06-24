package com.hz.learnkt.entity

import java.io.Serializable
import java.util.*
import javax.persistence.*

/** 微博
 * @Author hezhao
 * @Time   2018-06-24 17:08
 * @Description 无
 * @Version V 1.0
 */
@Entity
@Table(name = "weibo")
data class Weibo(
        @Id
        @Column(name= "id", nullable = false)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0L,

        @Column(name= "weibo_text", nullable = false)
        var weiboText: String = "",

        @Column(name= "create_date", nullable = false)
        var createDate: Date = Date(),

        // 多对一
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_id")
        var userInfo: UserInfo,

        // 一对多
        @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE], mappedBy = "weibo")
        var comments: List<Weibo>

): Serializable