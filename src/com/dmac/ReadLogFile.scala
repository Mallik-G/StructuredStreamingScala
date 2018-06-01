package com.dmac

import scala.io.Source

/**
  * Created by dharshekthvel on 9/9/17.
  */
object ReadLogFile extends App {


  val list = List(1,2,3,4,5)

  list.reduceLeft((x,y) => x+y)

  list.reduceRight((x,y) => x+y)

  list.reduce((x,y) => x+y)

  list.fold(10)((x,y) => x+y)

//  val lines = Source.fromFile("/home/dharshekthvel/java_error_in_IDEA_7548.log")
//                    .getLines()
//                    .toList


  //println(lines.takeRight(10))

}
