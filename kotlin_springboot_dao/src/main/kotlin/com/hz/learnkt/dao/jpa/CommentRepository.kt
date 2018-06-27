package com.hz.learnkt.dao.jpa

import com.hz.learnkt.entity.Comment
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.text.SimpleDateFormat
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

    /**
     * 使用最为基础的方法去写DAO
     */
    @Transactional(readOnly = true)
    fun searchComment(userName: String? = null, weiboText: String? = null, startDate: Date? = null, endDate: Date? = null, pageNo: Int? = null, pageSize: Int? = null): List<Comment> {
        // JPQL
        val jpql = StringBuffer("select c from Comment c join fetch c.userInfo u left join fetch c.weibo w where 1=1 ")
        val paramMap = HashMap<String, Any>()
        if (userName != null && !StringUtils.isEmpty(userName)) {
            jpql.append(" and u.userName = :userName")
            paramMap["userName"] = userName
        }
        if (weiboText != null && !StringUtils.isEmpty(weiboText)) {
            jpql.append(" and w.weiboText like :weiboText")
            paramMap["weiboText"] = "%$weiboText%"
        }

        if (startDate != null) {
            jpql.append(" and c.commentDate >= :startDate")
            paramMap["startDate"] = startDate
        }
        if (endDate != null) {
            jpql.append(" and c.commentDate <= :endDate")
            paramMap["endDate"] = endDate
        }

        val query = entityManager.createQuery(jpql.toString())
        val keys = paramMap.keys
        for (keyItem in keys) {
            query.setParameter(keyItem, paramMap[keyItem])
        }

        return if(pageNo != null && pageSize != null){
            var start = (pageNo - 1) * pageSize
            if (start < 0){
                start = 0
            }
            query.setFirstResult(start).setMaxResults(pageSize).resultList as List<Comment>
        } else {
            query.resultList as List<Comment>
        }
    }
}