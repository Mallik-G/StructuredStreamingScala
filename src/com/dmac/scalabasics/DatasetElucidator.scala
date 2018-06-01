package com.dmac.scalabasics

import org.apache.spark.sql.SparkSession

/**
  * Created by dharshekthvel on 5/1/18.
  */
object DatasetElucidator {


  def main(args : Array[String]) = {


    val ss = SparkSession.builder().appName("IBMSparkJOB").master("local[*]").getOrCreate()
    val sparkContext = ss.sparkContext

    import  ss.implicits._

    val list = List("Data", "inside", "dataset")
    val ds = ss.createDataset(list)


    val lengthDS = ds.map(each => each.length)

    val rdd = lengthDS rdd

    ss.createDataset(rdd)

    ds.foreach(each => println(each))
  }
}
