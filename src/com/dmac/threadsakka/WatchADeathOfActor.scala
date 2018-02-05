package com.dmac.threadsakka

import akka.actor.{Actor, ActorSystem, PoisonPill, Props, Terminated}

/**
  * Created by dharshekthvel on 19/11/17.
  */
object WatchADeathOfActor {


  def main(args : Array[String]) = {

    val as = ActorSystem.create("DeathWatch")
    val actor = as.actorOf(Props[Master], name = "Master")

    actor ! "start-worker"

    val worker = as.actorSelection("/user/Master/Worker")

    worker ! PoisonPill

  }
}


class Master1 extends Actor {


  override def receive = {


    case "start-worker" => {
      val worker = context.actorOf(Props[Worker], name = "Worker")
      context.watch(worker)
    }
    case Terminated(worker) => println("worker is killed")
    case _ => println("Master message received")
  }



}
class Worker extends Actor {

  override def receive = {

    case _ => println("Worker message received")
  }
}