package com.hz.learnkt.service

import com.hz.learnkt.entity.Weibo
import org.springframework.data.domain.Page
import java.util.*

/**
 * @Author hezhao
 * @Time   2018-06-24 23:15
 * @Description 无
 * @Version V 1.0
 */
interface WeiboService {
    fun getUserWeibo(userName: String): List<Weibo>

    fun searchWeibo(userName: String, weiboText: String, pageNo: Int, pageSize: Int): Page<Weibo>

    /**
     * JPA Criteria 动态查询
     */
    fun searchWeiboByCriteria(userName: String?, weiboText: String?, startDate: Date?, endDate: Date?, pageNo: Int, pageSize: Int): Page<Weibo>

    /**
     * 实例查询
     * 参考：https://www.cnblogs.com/rulian/p/6533109.html
     */
    fun searchWeiboByExampleMatcher(userName: String?, weiboText: String?): List<Weibo>

}