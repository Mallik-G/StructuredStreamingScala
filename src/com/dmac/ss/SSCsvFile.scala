package com.dmac.ss

import java.util.concurrent.TimeUnit

import org.apache.spark.sql.{SparkSession, types}
import org.apache.spark.sql.streaming.{OutputMode, Trigger}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

/**
  * Created by dharshekthvel on 22/8/17.
  */
object SSCsvFile {

  def main(args : Array[String]) = {


    val schema = StructType(
                            Array(StructField("AUTH_CODE", StringType),
                              StructField("SUBREQ", StringType),
                              StructField("AUA", StringType),
                              StructField("SA", StringType)))

    val sparkSession = SparkSession.builder()
                                    .appName("StructuredStreamingJOB")
                                    .master("local[*]")
                                    .getOrCreate()

    // Source
    // The schema is mandatory for streaming on the CSV file
    /*  eachLine Stream is the Input Table */
    val eachLineStream = sparkSession .readStream
                                      .option("header", "true")
                                      .schema(schema)
                                      .csv("/Users/dharshekthvel/ac/data")







    val streamWriter  = eachLineStream.writeStream.outputMode(OutputMode.Append()).trigger(Trigger.ProcessingTime(0, TimeUnit.SECONDS))
          .format("console")



    val streamingQuery = streamWriter.start()


    streamingQuery.awaitTermination()

  }

}
