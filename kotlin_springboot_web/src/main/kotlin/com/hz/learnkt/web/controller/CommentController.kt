package com.hz.learnkt.web.controller

import com.hz.learnkt.entity.Comment
import com.hz.learnkt.entity.Weibo
import com.hz.learnkt.service.CommentService
import com.hz.learnkt.service.WeiboService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.text.SimpleDateFormat
import java.util.*

/** 评论控制器
 *
 * Created by hezhao on 2018-06-25 11:47
 */
@RestController
@RequestMapping("/comments")
class CommentController {

    // 必须使用延迟、var属性才能注入
    @Autowired
    private lateinit var commentService: CommentService

    @GetMapping("/searchComment")
    fun searchComment(userName: String?, weiboText: String?, startDate: String?, endDate: String?, pageNo: Int, pageSize: Int): List<Comment> {
        var sDate:Date? = null
        var eDate:Date? = null
        if(startDate != null && endDate != null) {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            sDate = sdf.parse(startDate)
            eDate = sdf.parse(endDate)
        }

        return commentService.searchComment(userName, weiboText, sDate, eDate, pageNo, pageSize)
    }
}

// http://127.0.0.1:8082/api/comments/searchComment?userName=admin&weiboText=特金会&startDate=2018-06-01&endDate=2018-07-01&pageNo=1&pageSize=10