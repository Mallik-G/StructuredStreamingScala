package com.dmac.catslibrary

/**
  * Created by dharshekthvel on 8/9/17.
  */
object ScalaForEachAndMap {




  def main(args : Array[String]) = {


    val meshList = List ("Data0", "Data2", "Timelime0")

    meshList.foreach(new Function[String, Unit] {
      override def apply(v1: String): Unit = {
        println(v1)
      }
    })


    meshList.map(new Function[String, String] {
      override def apply(v1: String): String = {
        v1.toLowerCase
    }}).foreach(x => println(x))

    meshList.map(mapperFunction)

    println("Computed")

  }

  val mapperFunction = new Function[String, Unit] {
    override def apply(v1: String): Unit = {
      println(v1)
    }
  }

}
