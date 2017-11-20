package com.dmac.threadsakka

import akka.actor.{Actor, ActorSystem, PoisonPill, Props}

/**
  * Created by dharshekthvel on 14/11/17.
  */
object CreatingChildActors {

  def main(args : Array[String]) = {
    val system = ActorSystem("ClusterManager")
    // default Actor constructor
    val masterActor = system.actorOf(Props[SparkMaster], name = "master")


    masterActor tell("create-child", masterActor)

    //masterActor ! PoisonPill
  }
}


class SparkMaster extends Actor {

  def receive = {

    case "create-child" => {
      println("Received message in master")

      val range = 1 to 1000000000

      for (i <- range) {
        var name = "worker"
        val masterActor = context.actorOf(Props[SparkWorker], name = name+i)
        masterActor ! "process"
      }

    }
    case 0 => {
      println("Exiting actor.")
      context stop self
    }
    case _ => println("Executing in master")
  }
}


class SparkWorker extends Actor {

  def receive = {

    case "process" => {
      println("Processing message in worker")
    }
    case 0 => {
      println("Exiting actor.")
      context stop self
    }
    case _ => println("Executing in master")
  }
}