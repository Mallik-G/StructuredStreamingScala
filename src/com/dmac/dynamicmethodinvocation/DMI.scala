package com.dmac.dynamicmethodinvocation
import scala.language.dynamics

/**
  * Created by dharshekthvel on 30/10/17.
  */
object DMI {

  def main(args: Array[String]) = {

    val fad = new FraudAnalyticsData

    // The below would call the selectDynamic() method
    fad.name = "DATA_LEGITIMATE"
    fad.name = "DATA_LEGITIMATE_OVERRIDE"

    // The below method would call the applyDynamic() method
    fad.dMethod(1, "2", 3f)

    // The below method would call the applyDynamicNamed() method
    fad.nameMethodCall(data="citrix", data2="install_shield", data3="kafka")

    // The below would call the updateDynamic() method
    println(fad.name)
  }

}

class FraudAnalyticsData extends Dynamic
{

  var map = Map.empty[String, Any]

  def selectDynamic(name: String) = map.get(name)


  def updateDynamic(name: String)(value: Any) {
    map += name -> value
  }

  def applyDynamic(name: String)(args: Any*) = {
    for (each <- args) {
      println(name + each)
    }
  }

  def applyDynamicNamed(name: String)(args: (String, Any)*) = {
    for (each <- args) {
      println(name + each)
    }
  }


}
