package com.dmac.threadsakka

import akka.actor.{Actor, ActorIdentity, ActorLogging, ActorPath, ActorSystem, Identify, Props}

/**
  * Created by dharshekthvel on 20/11/17.
  */
object FindAllActorsUtility {

  def main(args : Array[String]) = {

    val system = ActorSystem("FindAllActorsUtility")
    val idActor = system.actorOf(Props[IDActor],"idActor");
    idActor ! "start"
  }
}

class IDActor extends Actor with ActorLogging {

  def receive = {
    case "start" =>
      println("Current Actors in system:")
      self ! ActorPath.fromString("akka://ClusterManager/user")

    case path: ActorPath =>
      context.actorSelection(path / "*") ! Identify(())

    case ActorIdentity(_, Some(ref)) =>
      println(ref.toString())
      self ! ref.path
  }
}
