package com.yuns.toy

import org.apache.spark.sql.{DataFrame, SparkSession}

class SparkTestApp {
	def run(spark: SparkSession, path: String)(reader: String => DataFrame): DataFrame = {
		reader(path).createOrReplaceTempView("tb_test")

		val df = spark.sql("""
			  select word, sum(cnt)
			  from (
			  		select explode(split(value, ' ')) word, 1 cnt
			  		from tb_test
			  )
			  group by word
			  """)
		df
	}
}

object SparkTestApp {
	def main(args: Array[String]): Unit = {
		val spark = SparkSession
		  .builder()
		  .appName("SparkTestApp")
		  .getOrCreate()

		val app = new SparkTestApp()

		val df = app.run(spark, s"${sys.env("SPARK_HOME")}/README.md") { path =>
			spark.read.text(path)
		}

		df.show
	}
}
