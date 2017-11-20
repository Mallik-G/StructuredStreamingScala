package com.dmac.futureevaluation




/**
  * Created by dharshekthvel on 18/11/17.
  */
object ExecuteDifferentTasks {


  def main(args : Array[String]) = {

    fetchDataFromTerradata onSuccess {
      case e => println(e)
    }

    fetchDataFromTerradata

    Thread.sleep(1000)
  }

  import scala.concurrent.Future
  implicit val executor = scala.concurrent.ExecutionContext.global

  val fetchDataFromTerradata = Future {

    println("Fetched data from TERRADATA_")

    "TERRADATA"
  }
}



