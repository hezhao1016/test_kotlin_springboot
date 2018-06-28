package com.hz.learnkt.service.impl

import com.hz.learnkt.dao.jpa.cluster.CityRepository
import com.hz.learnkt.dao.mybatis.xml.cluster.CityDao
import com.hz.learnkt.entity.cluster.City
import com.hz.learnkt.service.CityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by hezhao on 2018-06-27 17:49
 */
@Service
class CityServiceImpl: CityService {
    @Autowired
    private lateinit var cityRepository: CityRepository

    override fun findAll(): MutableList<City> = cityRepository.findAll()

}