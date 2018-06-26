package com.hz.learnkt.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.format.annotation.DateTimeFormat
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
        var id: Long? = 0L,

        @Column(name= "weibo_text", nullable = false)
        var weiboText: String? = "",

        @Column(name= "create_date", nullable = false)
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        var createDate: Date? = Date(),

        // 多对一
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        var userInfo: UserInfo? = null,

        // 一对多
        // mappedBy 把关系的维护交给多方对象的属性去维护关系, 只有OneToOne,OneToMany,ManyToMany上才有mappedBy属性，ManyToOne不存在该属性
        @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.MERGE], mappedBy = "weibo")
        @JsonIgnore
        var comments: Set<Comment>? = null

): Serializable