package com.dmac.ss

import java.util.concurrent.TimeUnit

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.{OutputMode, Trigger}

/**
  * Created by dharshekthvel on 22/8/17.
  */
object StructuredStreamingSocket {

  def main(args : Array[String]) = {


    val sparkSession = SparkSession.builder()
                                    .appName("StructuredStreamingJOB")
                                    .master("local[*]")
                                    .getOrCreate()

    // Source
    /*  eachLine Stream is the Input Table */
    val eachLineStream = sparkSession.readStream.format("socket")
                                              .option("host","localhost")
                                              .option("port","4567")
                                              .load()


    // Sink
//    val streamWriter  = eachLineStream.writeStream.outputMode(OutputMode.Append())
//                      .format("console")
//

    import sparkSession.implicits._
    val socketDS = eachLineStream.as[String]
    val wordDS = socketDS.flatMap(each => each.split(" "))
    val countDS = wordDS.groupBy("value").count()

    // Batch processing for the 10 seconds.
//    val streamWriter  = eachLineStream.writeStream.outputMode(OutputMode.Append()).trigger(Trigger.ProcessingTime(10, TimeUnit.SECONDS))
//      .format("console")


    val streamWriter  = countDS.writeStream.outputMode(OutputMode.Complete()).trigger(Trigger.ProcessingTime(10, TimeUnit.SECONDS))
          .format("console")


    val streamingQuery = streamWriter.start()


    eachLineStream.isStreaming
    eachLineStream.printSchema


    streamingQuery.awaitTermination()


    // checkpointing


  }

}
