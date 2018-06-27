package com.hz.learnkt.service

import com.hz.learnkt.entity.Comment
import java.util.*

/**
 * @Author hezhao
 * @Time   2018-06-24 23:15
 * @Description 无
 * @Version V 1.0
 */
interface CommentService {
    fun searchComment(userName: String? = null, weiboText: String? = null, startDate: Date? = null, endDate: Date? = null, pageNo: Int? = null, pageSize: Int? = null): List<Comment>
}