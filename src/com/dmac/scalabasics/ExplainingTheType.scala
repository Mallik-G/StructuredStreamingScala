package com.dmac.scalabasics

/**
  * Created by dharshekthvel on 8/11/17.
  */
object ExplainingTheType {


  def main(args: Array[String]) = {

  }
}

// Type lets you define the abstract behavior of a class/trait
trait FileFormatter {

  type T

  def formatFile(): T
}


class ParqueFileFormatter extends FileFormatter {

  override type T = String

  override def formatFile() = {
    "RETURN-PARQUE-FILE"
  }
}
