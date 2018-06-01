package com.dmac.ss

import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by dharshekthvel on 6/9/17.
  */
object BatchJOB {

  def main(args : Array[String]) = {

    val ss = SparkSession.builder().appName("IBMSparkJOB").master("local[*]").getOrCreate()
    val sparkContext = ss.sparkContext


    val csvDF = ss.sqlContext.read.option("header", "true").csv("/Users/dharshekthvel/temp/auth.csv")

    csvDF.createTempView("AUTH_TABLE")

    ss.sql("SELECT sa from AUTH_TABLE").show()

    sparkContext stop()

  }

}


case class AuthDTO(refID : String, aua : String)



























//
//sparkContext.textFile("/Users/dharshekthvel/temp/auth.csv")
//.flatMap(each => each.split(" "))
//.map(eachWord => (eachWord,1))
//.reduceByKey(_+_)
//.foreach(each => println(each._1 + each._2))