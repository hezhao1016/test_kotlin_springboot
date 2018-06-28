package com.hz.learnkt.dao.jpa.master

import com.hz.learnkt.entity.master.UserInfo
import com.hz.learnkt.entity.master.Weibo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional


/** 微博 Dao
 * @Author hezhao
 * @Time   2018-06-24 23:07
 * @Description 无
 * @Version V 1.0
 */
interface WeiboRepository :JpaRepository<Weibo, Long>, JpaSpecificationExecutor<Weibo> {

    @Query("select w from Weibo w where w.userInfo.userName = :userName")
    fun searchUserWeibo(@Param("userName") username: String): List<Weibo>

    /**
     * 排序
     */
    @Query("select w from Weibo w where w.userInfo.userName = :userName")
    fun searchUserWeibo(@Param("userName") username: String, sort: Sort): List<Weibo>

    @Modifying
    @Transactional(readOnly = false)
    @Query("update Weibo w set w.weiboText = :text where w.userInfo = :userInfo")
    fun setUserWeiboContent(@Param("text") weiboText: String, @Param("userInfo") userInfo: UserInfo): Int

    /**
     * 分页查询
     */
    fun findByUserInfoIsAndWeiboTextContaining(user: UserInfo, weiboText: String, pageable: Pageable): Page<Weibo>

    @Transactional(readOnly = false)
    fun deleteByUserInfo(userInfo: UserInfo): Int

}