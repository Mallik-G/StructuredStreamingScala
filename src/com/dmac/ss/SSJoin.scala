package com.dmac.ss

import java.util.concurrent.TimeUnit

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.{OutputMode, Trigger}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

/**
  * Inner Join on the streaming
  */
object SSJoin {

  def main(args : Array[String]) = {

    val sparkSession = SparkSession.builder()
      .appName("StructuredStreamingJOB")
      .master("local[*]")
      .getOrCreate()


    import sparkSession.implicits._

    val csvStream = sparkSession .read
      .option("header", "true")
      .csv("/Users/dharshekthvel/ac/data/salessimple.csv").as[SalesDTO]




    val eachLineStream = sparkSession.readStream.format("socket")
      .option("host","localhost")
      .option("port","4567")
      .load().as[String].map(each => StreamDTO(each))

    val joinedStream = csvStream.join(eachLineStream, "Producttype")


    val streamWriter  = joinedStream.writeStream.outputMode(OutputMode.Append()).trigger(Trigger.ProcessingTime(0, TimeUnit.SECONDS))
      .format("console")



    val streamingQuery = streamWriter.start()


    streamingQuery.awaitTermination()

  }

}


case class  StreamDTO(Producttype : String)

case class SalesDTO(Year: String,Productline: String,Producttype: String,Product: String,Ordermethodtype: String,Retailercountry: String,Revenue: String,Plannedrevenue: String,Productcost: String,Quantity: String,Unitcost: String,Unitprice: String,Grossprofit: String,Unitsaleprice: String)