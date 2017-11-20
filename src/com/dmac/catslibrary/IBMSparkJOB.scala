package com.dmac.catslibrary

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.execution.datasources.jdbc.JDBCRDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{Partitioner, SparkConf, SparkContext}


/**
  * Created by dharshekthvel on 12/10/17.
  */
object IBMSparkJOB extends App {


  val sparkSession = SparkSession.builder().appName("SQLJob")
                        .master("local[*]").getOrCreate()

  val ibmEmployeeList = List(101, 201, 300, 34, 76)

  val sc = sparkSession.sparkContext

  val ibmEMPRDD = sc.parallelize(ibmEmployeeList)

  val ibmPairRDD = ibmEMPRDD.map(eachEmployee => (eachEmployee,eachEmployee))

  val partitionedData = ibmPairRDD.partitionBy(new IBMEmployeePartitioner)


  partitionedData.mapPartitionsWithIndex((pI, dataIterator) =>

      dataIterator.map(data => if (pI == 2)
                                println(pI, data))

  )


  partitionedData.saveAsTextFile("")

}

class IBMEmployeePartitioner extends Partitioner {

  override def numPartitions: Int = {
    10
  }

  override def getPartition(key: Any): Int = {
    val empid = key.asInstanceOf[Int]

    if (empid < 100)
      1
    else if (empid < 200)
      2
    else
      3
  }
}