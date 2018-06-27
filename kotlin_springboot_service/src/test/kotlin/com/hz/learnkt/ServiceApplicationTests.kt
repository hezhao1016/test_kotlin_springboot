package com.hz.learnkt

import com.hz.learnkt.service.CommentService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
// 指定启动入口类
@SpringBootTest(classes = [ServiceApplication::class])
class ServiceApplicationTests {

	@Autowired
	private lateinit var commentService: CommentService

	@Test
	fun testSearchComment() {
		val list = commentService.searchComment(weiboText = "特金会")
		println("==================================testSearchComment==================================")
		list.forEach { println(it) }
		println("==================================testSearchComment==================================")
	}

}