package com.dmac

import scala.io.Source

/**
  * Created by dharshekthvel on 9/9/17.
  */
object ReadLogFile extends App {


  // In single quotes
  val character_literal = 'B'

  // In double quotes
  val string_datatype = "B"


  val dataArray = new Array[String](3)
  dataArray(0) = "CASSANDRA"
  dataArray.foreach(each => println(each))

  val noSQLArray = Array("Cassandra", "BigSQL", "REDIS", 100)
  noSQLArray.foreach(each => println(each))

//  val lines = Source.fromFile("/home/dharshekthvel/java_error_in_IDEA_7548.log")
//                    .getLines()
//                    .toList


  //println(lines.takeRight(10))

}
