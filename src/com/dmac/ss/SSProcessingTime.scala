package com.dmac.ss

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.OutputMode

/**
  * Created by dharshekthvel on 6/9/17.
  */
object SSProcessingTime {

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


    sparkSession.sparkContext.setLogLevel("ERROR")

    import sparkSession.implicits._

    val withTimeStamp = eachLineStream.withColumn("current_time_stamp", current_timestamp()).toDF("word", "current_time_stamp")


    val countWindow = withTimeStamp.groupBy(window($"current_time_stamp", "15 seconds"))
                                   .count()
                                   .orderBy("window")


    countWindow .writeStream
                  .option("truncate","false")
                  .outputMode(OutputMode.Complete())
                  .format("console")
                  .start()
                  .awaitTermination()

  }

}
