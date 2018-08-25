package com.dmac.scalabasics

class DataStructure {

  def getData() : String = {
    "DATA"
  }

  def setDATA(data : String) = {

  }

  private def complexDataStructreMatrx() = {

  }

}

object DataStructure {

  def apply(data : String) : DataStructure = {
    var ds = new DataStructure
    ds.setDATA(data)
    ds
  }

}
