package com.hz.learnkt

import com.hz.learnkt.dao.annotation.UserMapper
import com.hz.learnkt.entity.master.UserInfo
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class UserMapperTests {

	@Autowired
	private lateinit var userMapper: UserMapper

	@Test
	fun testQueryAll() {
		val userList = userMapper.queryAll()
		println("==================================testQueryAll==================================")
		userList.forEach { println(it) }
		println("==================================testQueryAll==================================")
	}

	@Test
	fun testQueryUserList() {
		val userInfo = UserInfo()
		userInfo.name = "b"
		val userList = userMapper.queryUserList(userInfo)
		println("==================================testQueryUserList==================================")
		userList.forEach { println(it) }
		println("==================================testQueryUserList==================================")
	}

	@Test
	fun testGetOne() {
		val user = userMapper.getOne(1)
		println("==================================testGetOne==================================")
		println(user)
		println("==================================testGetOne==================================")
	}

	@Test
	fun testInsertUser() {
		var count = userMapper.insertUser(UserInfo(name = "AA", userName = "aa", password = "123"))
		count += userMapper.insertUser(UserInfo(name = "BB", userName = "bb", password = "123"))
		println("==================================testInsertUser==================================")
		println("insert count is $count")
	}

	@Test
	fun testUpdateUser() {
		var count = userMapper.updateUser(UserInfo(name = "AA Updated", userName = "aa"))
		println("==================================testUpdateUser==================================")
		println("update count is $count")
	}

	@Test
	fun testDeleteUser() {
		var count = userMapper.deleteUser(7)
		println("==================================testDeleteUser==================================")
		println("delete count is $count")
	}

	@Test
	fun testSearchUser() {
		val userInfo = UserInfo()
		userInfo.id = 1
		userInfo.name = "ja"
		userInfo.userName = "admin"
		val userList = userMapper.searchUser(userInfo)
		println("==================================testSearchUser==================================")
		userList.forEach { println(it) }
		println("==================================testSearchUser==================================")
	}

	@Test
	fun testFindUser() {
		val userInfo = UserInfo()
		userInfo.id = 1
		userInfo.name = "ja"
		userInfo.userName = "admin"
		val userList = userMapper.findUser(userInfo)
		println("==================================testFindUser==================================")
		userList.forEach { println(it) }
		println("==================================testFindUser==================================")
	}

}