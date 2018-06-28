package com.hz.learnkt.entity.master

import org.springframework.format.annotation.DateTimeFormat
import java.io.Serializable
import java.util.*
import javax.persistence.*

/** 微博评论
 *
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
        var id: Long? = null,

        @Column(name= "comment_text", nullable = false)
        var commentText: String? = "",

        @Column(name= "comment_date", nullable = false)
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        var commentDate: Date? = Date(),

        // 多对一
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        var userInfo: UserInfo? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "weibo_id")
        var weibo: Weibo? = null

): Serializable {
        override fun toString(): String {
                return "Comment(id=$id, commentText=$commentText, commentDate=$commentDate, userInfo=$userInfo)"
        }
}