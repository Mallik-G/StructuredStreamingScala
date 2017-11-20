package com.dmac.ml


import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix
import org.apache.spark.sql.SparkSession
import org.apache.spark.mllib.stat.{MultivariateStatisticalSummary, Statistics}

/**
  * Created by dharshekthvel on 25/9/17.
  */
object StatisticsElucidator {

  def main(args : Array[String]) = {



    val sc = SparkSession.builder().appName("MLJOB")
      .master("local[*]").getOrCreate().sparkContext


    val observations = sc.parallelize(
      Seq(
        Vectors.dense(1.0, 10.0, 100.0),
        Vectors.dense(2.0, 20.0, 200.0),
        Vectors.dense(3.0, 30.0, 300.0)
      )
    )


//    val s = new RowMatrix(observations)
//    s.computeColumnSummaryStatistics()

    println("")


  }

}
