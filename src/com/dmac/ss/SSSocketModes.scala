package com.dmac.ss

import java.util.concurrent.TimeUnit

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.{OutputMode, Trigger}

/**
  * Created by dharshekthvel on 22/8/17.
  */
object SSSocketModes {

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


    import sparkSession.implicits._
    val socketDS = eachLineStream.as[String]

    val streamWriter  = socketDS.writeStream.outputMode(OutputMode.Update())
                                            .trigger(Trigger.ProcessingTime(10, TimeUnit.SECONDS))
                                            .format("console")

    val streamingQuery = streamWriter.start()

    streamingQuery.awaitTermination()

  }

}
