package com.hz.learnkt

import com.hz.learnkt.web.controller.api.WeiboController
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
// 指定启动入口类
@SpringBootTest(classes = [WebApplication::class])
class WebApplicationTests {

	@Autowired
	private lateinit var weiboController: WeiboController

	@Test
	fun testGetUserWeibo() {
		val userName = "admin"
		val userList = weiboController.getUserWeibo(userName)
		println("==================================testGetUserWeibo==================================")
		userList.forEach { println(it) }
		println("==================================testGetUserWeibo==================================")
	}
}