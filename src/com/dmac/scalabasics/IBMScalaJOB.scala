package com.dmac.scalabasics

import org.apache.spark.{Partitioner, SparkConf, SparkContext}

object IBMScalaJOB extends App {

  val conf = new SparkConf()
  conf.setAppName("IBMJob")
  conf.setMaster("local[*]")

  val sparkContext = new SparkContext(conf)

  val textFileRDD = sparkContext.textFile("/Users/dharshekthvel/ac/data/auth.csv")

  val auaRDD = textFileRDD.map(each => {
    val columns = each.split(",")
    (columns(2), columns(5))
  })

  //auaRDD.saveAsSequenceFile("hdfs://////")

  val paritionedRDD = auaRDD.partitionBy(new AUAParitioner)

//  paritionedRDD.mapPartitionsWithIndex((ibmPI, iterator) => {
//    iterator.map(each => {
//      if (ibmPI == 9) {
//        each
//      }
//    })
//  })

  paritionedRDD.saveAsTextFile("/Users/dharshekthvel/ac/data2")

  //auaRDD.foreach(each => println(each._1 + "   " +each._2))

}


class AUAParitioner extends Partitioner {

  override def numPartitions: Int = 10

  override def getPartition(key: Any): Int = {

    val inputKey = key.asInstanceOf[String]

    if (inputKey.equals("740000"))
      6
    else if (inputKey.equals("360000"))
      9
    else
      3
  }
}



