package com.dmac.scalabasics

object ImplicitGuy {




  def main(args : Array[String]) = {


    import ImplicitClassesFeature._

    val s = "IBM"
    s.letMoonBeAddedToString()
    s.letSuperStarBESuperStar()

  }


}


object ImplicitClassesFeature {

  implicit class AddFunctionalityToString(val input: String)
  {


    def letMoonBeAddedToString(): String = {
      input.concat("MOON")
    }


    def letSuperStarBESuperStar() = {

    }

  }

}