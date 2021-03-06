package com.hz.learnkt.service.impl

import com.hz.learnkt.dao.jpa.master.CommentRepository
import com.hz.learnkt.entity.master.Comment
import com.hz.learnkt.service.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


/**
 * Created by hezhao on 2018-06-25 09:38
 */
@Service
class CommentServiceImpl: CommentService {

    @Autowired
    private lateinit var commentRepository: CommentRepository

    override fun searchComment(userName: String?, weiboText: String?, startDate: Date?, endDate: Date?, pageNo: Int?, pageSize: Int?): List<Comment> {
        return commentRepository.searchComment(userName, weiboText, startDate, endDate, pageNo, pageSize)
    }

}