package com.dmac.scalabasics

/**
  * Created by dharshekthvel on 6/1/18.
  */
object CurryingFunctions {

  def elucidateMyNumber(first: Int)(second:Int)(third:Int) = {
    first+second+third
  }

  def main(args : Array[String]) = {



    val paf = elucidateMyNumber(1) (_)

    // [or]

    //val paf = elucidateMyNumber(1) _


    println(paf(2)(3))
  }
}
