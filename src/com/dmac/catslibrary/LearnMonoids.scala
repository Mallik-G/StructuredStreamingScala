package com.dmac.catslibrary

import cats.kernel.Monoid

/**
  * Created by dharshekthvel on 11/11/17.
  */
object LearnMonoids {

  var localVariable :String = _

  def main(args: Array[String]) = {

    print(localVariable)

    val dataList = List("A","B", "C")
    val dataListLast = List("X","Y", "Z")

    val finalList = listMonoid.combine(dataList,dataList)
    finalList.foreach(x => println(x))


    println(listMonoid.combine(listMonoid.combine(dataList,dataList), dataListLast))
    println(listMonoid.combine(dataListLast, listMonoid.combine(dataList,dataList)))

    println(listMonoid.combine(listMonoid.combine(dataList,dataList), listMonoid.empty))
    println(listMonoid.combine(listMonoid.empty, listMonoid.combine(dataList,dataList)))
  }

  def listMonoid[A] = new Monoid[List[A]] {
    override def empty: List[A] = List()

    override def combine(x: List[A], y: List[A]): List[A] = x ++ y
  }
}


