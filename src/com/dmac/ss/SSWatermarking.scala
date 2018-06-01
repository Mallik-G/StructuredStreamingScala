package com.dmac.ss

import org.apache.spark.sql.SparkSession

/**
  * Created by dharshekthvel on 4/20/18.
  */
object SSWatermarking {


  def main(args : Array[String]) = {

    val sparkSession = SparkSession.builder()
      .appName("StructuredStreamingJOB")
      .master("local[*]")
      .getOrCreate()

    /*  eachLine Stream is the Input Table */
    val eachLineStream = sparkSession.readStream.format("socket")
      .option("host","localhost")
      .option("port","4567")
      .load()


    eachLineStream.withWatermark("timestamp", "10 minutes")
                    .groupBy()


  }

}
