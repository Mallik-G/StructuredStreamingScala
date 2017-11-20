package com.dmac.ss

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StructType

/**
  * Created by dharshekthvel on 7/10/17.
  */
object StructuredStreamingFileStream {

  def main(args : Array[String]) = {

    val sparkSession = SparkSession.builder()
      .appName("FILE_STREAMING_JOB")
      .master("local[*]")
      .getOrCreate()


    //val schema = StructType


  }


}
