package com.hz.learnkt.dao.jpa

import com.hz.learnkt.entity.Comment
import com.hz.learnkt.entity.UserInfo
import org.apache.commons.lang3.StringUtils
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


/** 评论Dao
 * @Author hezhao
 * @Time   2018-06-25 0:17
 * @Description 无
 * @Version V 1.0
 */
@Repository
class CommentRepository{

    @PersistenceContext
    private lateinit var entityManager: EntityManager


    @Transactional(readOnly = true)
    fun searchWeiboByEm(username: String, weiboText: String, startDate: Date?, endDate: Date?, pageNo: Int, pageSize: Int): List<Comment> {
        val jpql = StringBuffer("select c from Comment c join fetch c.userInfo u left join fetch c.weibo w where 1=1 ")
        val paramMap = HashMap<String, Any>()
        if (!StringUtils.isEmpty(username)) {
            jpql.append(" and u.username = :username")
            paramMap["username"] = username
        }
        if (!StringUtils.isEmpty(weiboText)) {
            jpql.append(" and w.weiboText like :weiboText")
            paramMap["weiboText"] = "%$weiboText%"
        }
        if (startDate != null) {
            jpql.append(" and w.createDate >= :startDate")
            paramMap["startDate"] = startDate
        }
        if (endDate != null) {
            jpql.append(" and w.createDate <= :endDate")
            paramMap["endDate"] = endDate
        }

        val query = entityManager!!.createQuery(jpql.toString())
        val keys = paramMap.keys
        for (keyItem in keys) {
            query.setParameter(keyItem, paramMap[keyItem])
        }
        return query.setFirstResult(pageNo * pageSize).setMaxResults(pageSize).resultList as List<Comment>
    }
}