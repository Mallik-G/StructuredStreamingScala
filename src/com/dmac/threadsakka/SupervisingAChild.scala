package com.dmac.threadsakka

import java.lang.IllegalArgumentException

import scala.concurrent.duration._
import akka.actor.{Actor, ActorSystem, OneForOneStrategy, Props, SupervisorStrategy}
import akka.actor.Actor.Receive
import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}

/**
  * Created by dharshekthvel on 19/11/17.
  */
object SupervisingAChild {

  def main(args : Array[String]) = {

    val actorSystem = ActorSystem.create()
    val actor = actorSystem.actorOf(Props[NodeManager], name = "NodeManager")

    actor ! "ILAE"

    Thread.sleep(4000)
    actorSystem terminate()

    println("")
  }
}


class NodeManager extends Actor {

  override def preStart(): Unit = {

    println("PRE-START - Called by first actor instance during startup")
  }


  override def postStop(): Unit = {

    println("POST-STOP - Called by any actor instance during shutdown")
  }


  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {

    println("PRE-RESTART - Called by any running actor about to be restarted")
  }


  override def postRestart(reason: Throwable): Unit = {

    println("POST-RESTART - Called on a new instance of this actor after restart")
  }

  override def receive = {

    case "ILAE" => throw new IllegalArgumentException("M")
    case _ => println("Manager")
  }

//  override def supervisorStrategy: SupervisorStrategy =
//    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute, loggingEnabled = false) {
//
//    case _ : ArithmeticException => Resume
//    case _:  NullPointerException => Stop
//    case _: IllegalArgumentException => Restart
//    case _: Exception => Escalate
//  }

}

