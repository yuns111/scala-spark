package com.yuns.toy

import com.yuns.toy.util.PropertyUtils

object PropertyTestApp {
	def main(args: Array[String]): Unit = {
		val property = PropertyUtils.getStringValue("property.test.integer");

		println(property)
	}
}

