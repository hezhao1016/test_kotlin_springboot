package com.hz.learnkt

import com.hz.learnkt.dao.mybatis.xml.cluster.CityDao
import com.hz.learnkt.dao.mybatis.xml.master.UserInfoDao
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest
class CityDaoTests {

	@Autowired
	private lateinit var userInfoDao: UserInfoDao // 主数据源

	@Autowired
	private lateinit var cityDao: CityDao // 从数据源

	@Test
	fun testQuery() {
		val user = userInfoDao.findByUserName("admin")
		val city = cityDao.findByName("深圳市")

		println("==================================testQuery==================================")
		println(user)
		println(city)
		println("==================================testQuery==================================")
	}

}