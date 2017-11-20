package com.dmac

import org.apache.spark.graphx.{Edge, Graph}
import org.apache.spark.sql.SparkSession

/**
  * Created by dharshekthvel on 1/10/17.
  */
object metrolinx {

  def main(args : Array[String]) = {

    val sparkSession = SparkSession.builder().appName("MetrolinxJOB")
                                               .master("local[*]")
                                                  .getOrCreate()

    val sc = sparkSession.sparkContext

    val dataCompleteDF = sparkSession.read
                                  .option("header", "true")
                                .csv("/home/dharshekthvel/ac/code/scalatrainingintellij/data/trip_1.csv")


    // Show all data
    //dataCompleteDF.show(100)

//    val requiredDataDF = dataCompleteDF.select("VEHICLE_ID", "STOP_CODE", "POINT_ID", "POINT_NAME", "PSNGR_IN", "PSNGR_OUT", "PSNGR_LOAD", "POINT_CODE")
    val requiredDataDF = dataCompleteDF.selectExpr("VEHICLE_ID", "cast(STOP_CODE as int) STOP_CODE", "POINT_ID", "POINT_NAME", "PSNGR_IN", "PSNGR_OUT", "PSNGR_LOAD", "POINT_CODE")
    //dataCompleteDF.createOrReplaceTempView("TRIP_DETAIL")
    requiredDataDF.createOrReplaceTempView("TRIP_DETAIL")


//    val trip = sparkSession.sql("SELECT * FROM TRIP_DETAIL WHERE VEHICLE_ID=8334 ORDER BY STOP_CODE")

    val trip = sparkSession.sql("SELECT distinct(STOP_CODE), POINT_NAME FROM TRIP_DETAIL WHERE VEHICLE_ID=8334 ORDER BY STOP_CODE")

    //trip.show(1000)
    val tripRDD = trip.rdd.map(each => (each(0).asInstanceOf[Number].longValue(), each(1).asInstanceOf[String]))


  //val tripRDD = trip.rdd.map(each => (each.get(0), ""))



    val defaultUser = ("Missing")

    //trip.printSchema()
//
    val edges = sc.parallelize(Array(Edge(6, 47, "r1"), Edge(47, 52, "r2")))
//    sc.parallelize()
    val graph = Graph(tripRDD, edges, defaultUser)


    graph.vertices.foreach(each => println(each))

    graph.inDegrees

    println("Done")


  }
}
