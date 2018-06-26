package com.hz.learnkt.service.impl

import com.hz.learnkt.dao.jpa.UserRepository
import com.hz.learnkt.dao.jpa.WeiboRepository
import com.hz.learnkt.entity.UserInfo
import com.hz.learnkt.entity.Weibo
import com.hz.learnkt.service.WeiboService
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.*
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root


/**
 * @Author hezhao
 * @Time   2018-06-24 23:14
 * @Description 无
 * @Version V 1.0
 */
@Service
class WeiboServiceImpl: WeiboService{

    @Autowired
    private lateinit var userDao: UserRepository

    @Autowired
    private lateinit var weiboDao: WeiboRepository

    override fun getUserWeibo(userName: String): List<Weibo> {
        // val sort = Sort.Order(Sort.Direction.DESC, "id")
        val sort = Sort.by(Sort.Order(Sort.Direction.DESC, "id"))
        return weiboDao.searchUserWeibo(userName, sort)
    }

    override fun searchWeibo(userName: String, weiboText: String, pageNo: Int, pageSize: Int): Page<Weibo> {
        val user = userDao.findByUserName(userName)
        if (user != null) {
            // pageNo从0开始
            return weiboDao.findByUserInfoIsAndWeiboTextContaining(user, "%$weiboText%", PageRequest.of(pageNo, pageSize))
        }
        return PageImpl(emptyList())
    }

    override fun searchWeiboByCriteria(userName: String?, weiboText: String?, startDate: Date?, endDate: Date?, pageNo: Int, pageSize: Int): Page<Weibo> {
        return weiboDao.findAll( {
            root, criteriaQuery, criteriaBuilder ->

            var predicates = mutableListOf<Predicate>()

            if (StringUtils.isNotBlank(userName)) {
                predicates.add(criteriaBuilder.equal(root.get<UserInfo>("userInfo").get<String>("userName"), userName))
            }
            if (StringUtils.isNotBlank(weiboText)) {
                predicates.add(criteriaBuilder.like(root.get("weiboText"), "%$weiboText%"))
            }
            if (startDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get<Any>("createDate").`as`(Date::class.java), startDate))
            }
            if (endDate != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get<Any>("createDate").`as`(Date::class.java), endDate))
            }

            return@findAll criteriaBuilder.and(*predicates.toTypedArray())
        },  PageRequest.of(pageNo, pageSize))
    }

    override fun searchWeiboByExampleMatcher(userName: String?, weiboText: String?): List<Weibo> {
        //创建查询条件数据对象
        val weibo = Weibo()
        weibo.userInfo = UserInfo()

        // 先把一些不需要作为查询条件的属性设置为null
        weibo.id = null
        weibo.weiboText = null
        weibo.createDate = null
        weibo.userInfo?.id = null
        weibo.userInfo?.name = null
        weibo.userInfo?.password = null
        weibo.userInfo?.userName = null

        if(userName != null && StringUtils.isNotBlank(userName)){
            weibo.userInfo?.userName = userName
        }
        if(weiboText != null && StringUtils.isNotBlank(weiboText)) {
            weibo.weiboText = weiboText
        }

        //创建匹配器，即如何使用查询条件
        val matcher = ExampleMatcher.matching() //构建对象
                // .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                // .withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
                .withMatcher("weiboText", ExampleMatcher.GenericPropertyMatchers.contains()) // 微博正文采用“包含”的方式查询
                // .withIgnorePaths("xxx")  //忽略属性

        //创建实例
        val ex = Example.of(weibo, matcher)

        //查询
        return weiboDao.findAll(ex)
    }

}