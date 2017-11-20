package com.dmac.futureevaluation

import java.util.concurrent.{ExecutorService, Executors}



/**
  * Created by dharshekthvel on 18/11/17.
  */
object ExecuteFutureWithAExecutor {

  import scala.concurrent.Future


  def main(args : Array[String]) = {

    implicit val executor = scala.concurrent.ExecutionContext.global

    Future {

      println("SELECT * FROM DB2")

      "DB2"
    }(executor).onSuccess {
      case out => println(out)
    }

    Thread.sleep(1000)
  }





}
