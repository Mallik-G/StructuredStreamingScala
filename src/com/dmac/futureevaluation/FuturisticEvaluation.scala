package com.dmac.futureevaluation

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by dharshekthvel on 17/11/17.
  */
object FuturisticEvaluation {

  def main(args : Array[String]) = {

    executeMeFuturistically onComplete {

        case Success(value) => println(value)
        case Failure(value) => println(value)

    }

    executeMeFuturistically onSuccess {
      case outVal => println(outVal)
    }

    executeMeFuturistically onFailure {
      case outVal => println(outVal)
    }

    Thread.sleep(5000)
  }


  val executeMeFuturistically = Future {
    Thread.sleep(1000)
    println(1+1)
    1+1
  }



}
