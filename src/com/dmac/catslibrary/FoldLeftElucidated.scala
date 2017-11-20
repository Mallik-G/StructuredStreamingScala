package com.dmac.catslibrary

/**
  * Created by dharshekthvel on 25/9/17.
  */
object FoldLeftElucidated {

  def main(args: Array[String]) = {


    def length(list: List[String]): Int = list.foldLeft(0) { (count, _) => count + 1 }

    def lastElement(list: List[String]): String = list.foldLeft("") { (a, b) => b }



    val myList = List("FUNCTOR",
                      "APPLICATIVE",
                      "SEMIGROUP")


    val size = length(myList)

    val last = lastElement(myList)


    println(last)


  }

}
