package com.hz.learnkt.dao.mybatis.xml.cluster

import com.hz.learnkt.entity.cluster.City
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/** 城市 DAO 接口类
 * Created by hezhao on 2018-06-27 17:26
 */
interface CityDao {
    /**
     * 根据城市名称，查询城市信息
     *
     * @param cityName 城市名
     */
    fun findByName(@Param("cityName") cityName: String): City
}