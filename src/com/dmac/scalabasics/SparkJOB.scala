package com.dmac.scalabasics

import org.apache.spark.sql.SparkSession


/**
  * Created by dharshekthvel on 5/1/18.
  */
object SparkJOB {


  def main(args : Array[String]) = {


    //println(Runtime.getRuntime.availableProcessors())


    val ss = SparkSession.builder()
                          .appName("IBMSparkJOB")
                          .master("local[*]")
                          .getOrCreate()

    val sparkContext = ss.sparkContext


    //Resilent - Feature

    val textFileRDD = sparkContext.textFile("/Users/dharshekthvel/ac/data/auth.csv")



    val auaRDD = textFileRDD.map(each => {
      val columns = each.split(",")
      columns(2)
    })


    val lengthRDD = auaRDD.map(each => each.length)

    lengthRDD.foreach(each => println(each))



    val authcsv = ss.sqlContext.read.option("header", "true").
              csv("/Users/dharshekthvel/ac/data/auth.csv")

    val aua = authcsv.select("aua")

    aua.rdd.filter(each => each.equals("30000"))

    authcsv.createOrReplaceTempView("AUTH_TABLE")

    ss.sql("SELECT aua, asa from AUTH_TABLE").show(20)



    //authcsv.show(20)

  }
}
