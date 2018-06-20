package com.hz.learnkt.entity

import java.io.Serializable

/**
 * Created by hezhao on 2018-06-19 17:09
 */

data class UserInfoDto(
        var id: Long = 0L,

        var name: String? = null,

        var userName: String = "",

        var password: String = ""

): Serializable