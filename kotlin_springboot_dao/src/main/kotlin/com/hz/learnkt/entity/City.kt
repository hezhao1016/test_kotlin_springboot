package com.hz.learnkt.entity

/** 城市信息
 *
 * Created by hezhao on 2018-06-19 17:09
 */
data class City(

        /** 城市编号 */
        var id: Long? = null,

        /** 省份编号 */
        var provinceId: Long? = null,

        /** 城市名称 */
        var cityName: String? = "",

        /** 描述 */
        var description: String? = ""
)