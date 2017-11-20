package com.dmac.ss

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by dharshekthvel on 6/9/17.
  */
object BatchJOB {

  def main(args : Array[String]) = {



    val sparkConfig = new SparkConf()
    sparkConfig.setAppName("IBMSparkJOB")
    sparkConfig.setMaster("local[*]")

    val sparkContext = new SparkContext(sparkConfig)



    sparkContext.textFile("/home/a.txt")
                .flatMap(each => each.split(" "))
                .map(eachWord => (eachWord,1))
                .reduceByKey(_+_)
                .foreach(each => println(each._1 + each._2))

  }

}
