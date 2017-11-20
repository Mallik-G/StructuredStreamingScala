package com.dmac

import scala.io.Source

/**
  * Created by dharshekthvel on 9/9/17.
  */
object ReadCSV extends App {

  val bufferedSource = Source.fromFile("/tmp/finance.csv")

  for (line <- bufferedSource.getLines()) {
    val cols = line.split(",").map(_.trim)
    println(cols(0))
  }

  bufferedSource.close

}
