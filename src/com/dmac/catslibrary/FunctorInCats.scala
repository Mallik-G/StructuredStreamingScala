package com.dmac.catslibrary

import cats._
import cats.instances.all._
import cats.syntax.functor._

/**
  * Created by dharshekthvel on 7/9/17.
  */
object FunctorInCats {


  def main(args : Array[String]) = {


    // [1] Way of implementing a functor
    val lengthofstring: String => Int = _.length

    val list = List ("Data", "Ingestion")

    val lengthOfList = list.fproduct(lengthofstring)

    println(lengthOfList)
    println(lengthOfList.toMap)


    // [2] Second way of implementing a functor
    implicit val listFunctor: Functor[List] = new Functor[List] {
      def map[A, B](fa: List[A])(f: A => B) = fa map f
    }

    val resultList = listFunctor.map(list)(_.length)
    println(resultList)


    // [3] Third way of implementing a Functor
    val functorList = Functor[List].map(List("HBase", "FLASK-SERVER-INGESTION"))(_.length)
    println(functorList)


  }

}
