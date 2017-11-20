package com.dmac.ml

import org.apache.spark.ml.classification.LogisticRegression

/**
  * Created by dharshekthvel on 25/9/17.
  */
object Regression {


  def main(args: Array[String]) = {


    val accn = List("regression", "svm")


    val algoSeq = Seq(1,5,"regression", "svm","tensor")
    algoSeq.foreach(each => println(each))


    println("Regression analyzed.")


  }

}
