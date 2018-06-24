package com.hz.learnkt.service.impl

import com.hz.learnkt.dao.jpa.UserRepository
import com.hz.learnkt.dao.jpa.WeiboRepository
import com.hz.learnkt.entity.Weibo
import com.hz.learnkt.service.WeiboService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

/**
 * @Author hezhao
 * @Time   2018-06-24 23:14
 * @Description 无
 * @Version V 1.0
 */
@Service
class WeiboServiceImpl: WeiboService{

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var weiboRepository: WeiboRepository

    override fun getUserWeibo(userName: String): List<Weibo> {
        return weiboRepository.searchUserWeibo(userName, Sort(Sort.Order(Sort.Direction.DESC, "id")))
    }

    override fun searchWeibo(userName: String, weiboText: String, pageNo: Int, pageSize: Int): Page<Weibo> {
        val user = userRepository.findByUserName(userName)
        if (user != null){
            return weiboRepository.findByUserIsAndWeiboTextContaining(user, weiboText, PageRequest(pageNo, pageSize))
        }
        return PageImpl(emptyList())
    }

}