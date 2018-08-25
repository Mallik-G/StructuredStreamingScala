package com.dmac.scalabasics

import org.apache.spark.sql.{SparkSession, functions}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

object StarDogNoSQLObject {


  def main(args : Array[String]) = {


        val ss = SparkSession .builder()
                              .appName("IBMJob")
                              .master("local[*]")
                              .getOrCreate()

        val csvDF = ss.sqlContext.read
                                  .option("header", true)
                                  .csv(
                                    "/Users/dharshekthvel/ac/data/auth.csv")

        val jdbcDF = ss.read.format("jdbc").options(Map("url" -> "jdbc:mysql://192.168.1.1/slz_core",
                                                  "user" -> "username", "password" -> "zzz")
                                              )


        ss.catalog.refreshTable("")
        ss.catalog.clearCache()
        ss.catalog.cacheTable("")


        csvDF.createGlobalTempView("AUTH_GLOBAL_TABLE")

        csvDF.createOrReplaceTempView("AUTH_TABLE")

        val selectDF = ss.sql("SELECT * FROM AUTH_TABLE")


        selectDF.printSchema()
        val dataCleansedDF = selectDF.withColumn("subreq_id",
                                      convertToInteger(selectDF.col("subreq_id")))

        dataCleansedDF.printSchema()

        csvDF.write.csv("/output.csv")
        //selectDF.rdd

        //csvDF.printSchema()
        csvDF.show(30)

  }

  import org.apache.spark.sql.{SparkSession, functions}
  val convertToInteger = functions.udf[Int, String](_.toInt)
}

case class CountryDTO(countryCode : Int, countryName : String)

