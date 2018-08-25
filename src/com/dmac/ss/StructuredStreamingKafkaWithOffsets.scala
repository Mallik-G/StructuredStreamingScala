package com.dmac.ss

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.OutputMode

/**
  * Created by dharshekthvel on 22/8/17.
  */
object StructuredStreamingKafkaWithOffsets {

  def main(args : Array[String]) = {


    val sparkSession = SparkSession.builder()
                                    .appName("StructuredStreamingJOB")
                                    .master("local[*]")
                                    .getOrCreate()

    import sparkSession.implicits._

    /*  eachLine Stream is the Input Table */
    val eachLineStream = sparkSession.readStream.format("kafka")
                                              .option("kafka.bootstrap.servers","localhost:9092")
                                              .option("subscribe","ANZ")
                                              //.option("startingOffsets", """{"ANZ":{"0":47}""")
                                              .option("startingOffsets", "earliest")
                                              //.option("endingOffsets", {"ANZ":{"0":50}""")
                                              .load()

    val kafkaStream = eachLineStream.selectExpr("CAST(key AS STRING)", "cast(value AS STRING)").as[(String,String)]


//
    val finalReckoner  = kafkaStream.writeStream.outputMode(OutputMode.Append())
                                    .format("console").start()
//
//    eachLineStream.isStreaming
////    eachLineStream.printSchema
//
   finalReckoner.awaitTermination()



  }

}
