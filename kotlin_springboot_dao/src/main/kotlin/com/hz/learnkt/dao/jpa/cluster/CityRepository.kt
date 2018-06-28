package com.hz.learnkt.dao.jpa.cluster

import com.hz.learnkt.entity.cluster.City
import org.springframework.data.jpa.repository.JpaRepository

/** 城市Dao
 * Created by hezhao on 2018-06-19 17:32
 */
interface CityRepository : JpaRepository<City, Long>{

}