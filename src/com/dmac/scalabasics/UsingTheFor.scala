package com.dmac.scalabasics

/**
  * Created by dharshekthvel on 8/11/17.
  */
object UsingTheFor {


  def main(args: Array[String]) = {


    val meshList = List("Contract_1", "Contract_2", "Contract_3")

    for (each <- meshList if (each.endsWith("_1")))
      println(each)
  }
}
