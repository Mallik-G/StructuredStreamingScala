package com.dmac.threadsakka

import akka.actor.{Actor, ActorSystem, PoisonPill, Props}
import akka.actor.Actor.Receive

/**
  * Created by dharshekthvel on 18/11/17.
  */
object LifeCycleOfAActor {

  def main(args : Array[String]) = {

    val actorSystem = ActorSystem.create()
    val actor = actorSystem.actorOf(Props[LIFE_CYCLE_OF_ACTOR], name = "LIFE_CYCLE_OF_ACTOR")

    actor ! "GIVE_ACTOR_A_LIFE"

    //actor ! PoisonPill
    actorSystem terminate()

    println("")
  }

}

class LIFE_CYCLE_OF_ACTOR extends Actor {


  override def receive: Receive = {
    case outValue => println(outValue)
  }

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
}
