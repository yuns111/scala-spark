package com.yuns.toy.util

import java.io.InputStreamReader
import java.util.Properties

import scala.collection.JavaConverters
import scala.io.Source

object PropertyUtils {
	val PROPERTY_FILE_NAME: String = "redis.properties"
	val COMMA_SEPARATOR: String = ","
	var propertiesMap: Map[String, String] = Map[String, String]()

	def loadAllProperties(): Unit = {
		val propertyFile: InputStreamReader = Source.fromResource(PROPERTY_FILE_NAME).reader()

		val properties = new Properties
		try {
			properties.load(propertyFile)

			val keySet = JavaConverters.asScalaSet(properties.stringPropertyNames())
			for(key <- keySet) {
				propertiesMap += (key -> properties.getProperty(key))
			}

		}catch {
			case e: Exception => println(e)
		}
	}

	def getStringValue(propertyName: String): String = {
		if(propertiesMap.isEmpty) {
			loadAllProperties()
		}

		propertiesMap.get(propertyName).orNull
	}

	def getIntValue(propertyName: String): Int = {
		if(propertiesMap.isEmpty) {
			loadAllProperties()
		}

		val property = propertiesMap.get(propertyName).orNull
		property.toInt
	}
}
