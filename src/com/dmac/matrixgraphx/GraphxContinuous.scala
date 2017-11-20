package com.dmac.matrixgraphx

import org.apache.spark.graphx.{Edge, Graph}
import org.apache.spark.sql.SparkSession

/**
  * Created by dharshekthvel on 25/9/17.
  */
object GraphxContinuous {

  def main(args: Array[String]) = {

    val sc = SparkSession.builder().appName("GRAPHXJOB")
      .master("local[*]").getOrCreate().sparkContext




    val users =
      sc.parallelize(Array((3L, ("rxin", "student")), (7L, ("jgonzal", "postdoc")),
        (5L, ("franklin", "prof")), (2L, ("istoica", "prof"))))

    val relationships =
      sc.parallelize(Array(Edge(3L, 7L, "collab"),    Edge(5L, 3L, "advisor"),
        Edge(2L, 5L, "colleague"), Edge(5L, 7L, "pi")))

    val defaultUser = ("John Doe", "Missing")

    val graph = Graph(users, relationships, defaultUser)


    graph.edges.foreach(each => println(each))



  }

}
