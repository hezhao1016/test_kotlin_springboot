package com.hz.learnkt.entity

import java.io.Serializable
import java.util.*
import javax.persistence.*

/** 微博评论
 * @Author hezhao
 * @Time   2018-06-24 17:08
 * @Description 无
 * @Version V 1.0
 */
@Entity
@Table(name = "comment")
data class Comment(
        @Id
        @Column(name= "id", nullable = false)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0L,

        @Column(name= "comment_text", nullable = false)
        var commentText: String = "",

        @Column(name= "create_date", nullable = false)
        var createDate: Date = Date(),

        // 多对一
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_id")
        var userInfo: UserInfo,

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "weibo_id")
        var weibo: Weibo

): Serializable