package com.dmac.scalabasics

/**
  * Created by dharshekthvel on 13/11/17.
  */
object OperatorOverloading {

  def main(args: Array[String]) = {
    val ofContract = new Contracts("OF365","3-Days")
    val bhiveContract = new Contracts("BHIVE","5-Days")


    val addedContracts = ofContract + bhiveContract

    val notBHive = !bhiveContract

    addedContracts toString()
    notBHive toString()

    println("")
  }
}


class Contracts(val name : String, val timeLine : String) {

  def + (that:Contracts) : Contracts = {
    new Contracts(this.name + " " + that.name, this.timeLine + " " + that.timeLine)
  }


  override def toString: String = {
    println("Contract - " + this.name + " ,Timeline =  " + this.timeLine)
    "None"
  }

  def unary_! : Contracts = {
    new Contracts(this.name.reverse, this.timeLine.reverse)
  }
}
