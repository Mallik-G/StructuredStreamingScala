package com.dmac.futureevaluation

import scala.concurrent.{Future, Promise}



/**
  * Created by dharshekthvel on 18/11/17.
  */
object PromiseMeSomething {

  implicit val executor = scala.concurrent.ExecutionContext.global


  def main(args : Array[String]) = {

    executeQueryAndFetchDataFromCassandara onSuccess {
      case out => out
    }
  }


  val executeQueryAndFetchDataFromCassandara = Future {

    val promise = Promise[String]()

    println("Fetched data from CASSANDRA")

    promise.success("CASSANDRA")

  }
}
