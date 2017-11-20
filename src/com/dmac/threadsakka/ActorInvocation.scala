package com.dmac.threadsakka

import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by dharshekthvel on 9/11/17.
  * A base thread invocation using the actor akka model
  */
object ActorInvocation {

  def main(args: Array[String]): Unit = {

    val actorSystem = ActorSystem.create()
    val masterActor = actorSystem.actorOf(Props[Master], name = "Master")

    // Actors can be of strings, case_classes etc..,
    masterActor ! "MASTER-HIT"

    masterActor ! MeshData("TIME_LINE", 99876)

    // Either ! or tell. Both are same.
    masterActor.tell(999,masterActor)

    actorSystem.terminate
  }
}

class Master extends Actor {

  def receive = {

    case "MASTER-HIT" => println("Received message in master")
    case 999 => println("Received message in master 999")
    case MeshData(timeline: String, id: Int) => println(s"The timeline data is $timeline and id is $id")
    case _ => println("Executing in master")
  }
}


case class MeshData(timeline: String, id: Int)