package com.hz.learnkt.service

import com.hz.learnkt.entity.Weibo
import org.springframework.data.domain.Page

/**
 * @Author hezhao
 * @Time   2018-06-24 23:15
 * @Description 无
 * @Version V 1.0
 */
interface WeiboService {
    fun getUserWeibo(userName: String): List<Weibo>

    fun searchWeibo(userName: String, weiboText: String, pageNo: Int, pageSize: Int): Page<Weibo>

}