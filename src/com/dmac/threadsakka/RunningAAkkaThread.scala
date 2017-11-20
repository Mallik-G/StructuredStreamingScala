package com.dmac.threadsakka

import akka.actor.{Actor, ActorSystem, Props}
import akka.util.Timeout
import akka.pattern.ask

import scala.concurrent.Await
import scala.concurrent.duration.DurationDouble

/**
  * Created by dharshekthvel on 9/11/17.
  *
  * Sending a message and an actor responding back
  */
object RunningAAkkaThread {


  def main(args : Array[String]) = {
    val system = ActorSystem("HelloSystem")
    // default Actor constructor
    val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")

    // Fire and forget
    helloActor ! "hello"

    implicit val timeout = Timeout(5 seconds)
    val returnMessage = helloActor ? "ack"

    val result = Await.result(returnMessage, timeout.duration).asInstanceOf[String]
    println(result)
    helloActor ! "random message"


    system terminate()

    println("")
  }
}

class HelloActor extends Actor {
  def receive = {
    case "hello" => println("hello back at you")
    case "ack" => sender() ! "message-received"
    case _       => println("huh?")
  }
}
