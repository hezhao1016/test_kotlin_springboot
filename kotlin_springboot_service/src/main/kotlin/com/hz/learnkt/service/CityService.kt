package com.hz.learnkt.service

import com.hz.learnkt.entity.cluster.City

interface CityService {
    fun findAll(): MutableList<City>
}