package com.dmac.catslibrary

/**
  * Created by dharshekthvel on 25/9/17.
  */
object MonadicLaws {


  def main(args : Array[String]) = {


    // Difference between a flatMap and map
    val computationFunction = (i: Int) => List(i*4)

    val originalList = List(1,2,3)


    val mappedList = originalList.map(computationFunction)

    val flattenedList = originalList.map(computationFunction).flatten
    val flatMapList = originalList.flatMap(computationFunction)

    println(mappedList)         // List(List(4), List(8), List(12))
    println(flattenedList)      // List(4, 8, 12)
    println(flatMapList)        // List(4, 8, 12)



  }

}
