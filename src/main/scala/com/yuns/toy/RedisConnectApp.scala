package com.yuns.toy

class RedisConnectApp {
	val redisMasterHost = "localhost" // host 정보
	val redisMasterPort = 6379
	val redisMasterTimeoutMs = 60000
	val redisMasterPassword = "password"

	import com.redis._

	def connect(): Unit = {
		val redis = new RedisClient(redisMasterHost, redisMasterPort, secret = Some(redisMasterPassword), timeout = redisMasterTimeoutMs)

		redis.zadd("test1", 1, "value")
		val test = redis.get("test1")
		println(test)

		val data = redis.zcard("key;data")
		println(data)
	}
}

object RedisConnectApp {
	def main(args: Array[String]): Unit = {
		val app = new RedisConnectApp()
		app.connect()
	}
}