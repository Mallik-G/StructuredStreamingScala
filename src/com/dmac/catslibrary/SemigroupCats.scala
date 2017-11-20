package com.dmac.catslibrary

import cats.Semigroup

/**
  * Created by dharshekthvel on 7/9/17.
  */
object SemigroupCats {

  def main(args : Array[String]) = {

    import cats.Semigroup
    import cats.implicits._

    val sg = Semigroup[Int].combine(3,4)
    val sgOne = 3 |+| 4

    println(sgOne)
    println(sg)

    // combine(x, combine(y, z)) = combine(combine(x, y), z)

    val semigroup = Semigroup[Int => Int]

    val a = Semigroup[Int => Int].combine({ (x: Int) => x + 1},
                                          { (y: Int) => y * 10})

    val b = Semigroup[Int => Int].combine({ (x: Int) => x + 2},
                                          { (x: Int) => x * 20})

    //import com.dmac.catslibrary.CustomClassSemigroup._
    //val e = Semigroup[RedisData].combine(RedisData("D"),RedisData("E"))

    //print(e)
//    val semigroupRedisData = Semigroup[RedisData => RedisData].combine({ (x: RedisData) => RedisData(x.avroData.concat("___"))},
//                                                                       { (y: RedisData) => RedisData(y.avroData.concat("****"))})
    val c = a |+| b

    print (c(1))

    // Wont work becasue RedisData is not
    //val d = RedisData("") |+| RedisData("")



  }

}


trait CustomClassSemigroup {
  implicit object customClassSemigroupImpl extends RedisDataSemigroupImpl
}

trait RedisDataSemigroupImpl extends Semigroup[RedisData] {
  def combine(first: RedisData, second: RedisData): RedisData = {
    RedisData("NEW_DATA")
  }

}



case class RedisData(avroData:String)
