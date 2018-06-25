package com.hz.learnkt.web.controller

import com.hz.learnkt.entity.Weibo
import com.hz.learnkt.service.WeiboService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.text.SimpleDateFormat
import java.util.*

/** 微博控制器
 *
 * Created by hezhao on 2018-06-25 11:47
 */
@RestController
@RequestMapping("/weibos")
class WeiboController {

    // 必须使用延迟、var属性才能注入
    @Autowired
    private lateinit var weiboService: WeiboService

    @GetMapping("/getUserWeibo")
    fun getUserWeibo(userName: String): List<Weibo> {
        return weiboService.getUserWeibo(userName)
    }

    @GetMapping("/searchWeibo")
    fun searchWeibo(userName: String, weiboText: String, pageNo: Int, pageSize: Int): Page<Weibo> {
        var pageNumber = pageNo
        if(pageNo >= 1) {
            pageNumber = pageNo - 1
        }

        return weiboService.searchWeibo(userName, weiboText, pageNumber, pageSize)
    }

    @GetMapping("/searchWeiboByCriteria")
    fun searchWeiboByCriteria(userName: String?, weiboText: String?, startDate: String?, endDate: String?, pageNo: Int, pageSize: Int): Page<Weibo> {
        var sDate:Date? = null
        var eDate:Date? = null
        if(startDate != null && endDate != null) {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            sDate = sdf.parse(startDate)
            eDate = sdf.parse(endDate)
        }

        var pageNumber = pageNo
        if(pageNo >= 1) {
            pageNumber = pageNo - 1
        }

        return weiboService.searchWeiboByCriteria(userName, weiboText, sDate, eDate, pageNumber, pageSize)
    }

    @GetMapping("/searchWeiboByExampleMatcher")
    fun searchWeiboByExampleMatcher(userName: String?, weiboText: String?): List<Weibo> {
        return weiboService.searchWeiboByExampleMatcher(userName, weiboText)
    }
}

// http://127.0.0.1:8082/api/weibos/getUserWeibo?userName=admin
// http://127.0.0.1:8082/api/weibos/searchWeibo?userName=admin&weiboText=特金会&pageNo=1&pageSize=10
// http://127.0.0.1:8082/api/weibos/searchWeiboByCriteria?userName=admin&weiboText=特金会&startDate=2018-06-01&endDate=2018-07-01&pageNo=1&pageSize=10
// http://127.0.0.1:8082/api/weibos/searchWeiboByExampleMatcher?userName=admin&weiboText=特金会