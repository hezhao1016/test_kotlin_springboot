package com.hz.learnkt.entity.cluster

import javax.persistence.*

/** 城市信息
 *
 * Created by hezhao on 2018-06-19 17:09
 */
@Entity
@Table(name = "city")
data class City(

        /** 城市编号 */
        @Id
        @Column(name= "id", nullable = false)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        /** 省份编号 */
        @Column(name= "province_id", nullable = false)
        var provinceId: Long? = null,

        /** 城市名称 */
        @Column(name= "city_name", nullable = false)
        var cityName: String? = "",

        /** 描述 */
        @Column(name= "description")
        var description: String? = ""
)