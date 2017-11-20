package com.dmac

import scala.io.Source

/**
  * Created by dharshekthvel on 9/9/17.
  */
object ContinuouslyReadLines extends App {



  val file = "/var/log/syslog"
  val tail = Seq("tail", "-f", file)


  println(tail)
  import scala.sys.process._
  tail.lineStream.foreach(println(_))






}
