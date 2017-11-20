package com.dmac.sparkjava;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * Created by dharshekthvel on 14/11/17.
 */
public class DataCockpitJob {

    public static void main(String args[]) {


        SparkConf sparkConfig = new SparkConf()
                                    .setAppName("DataCockpitJOB")
                                    .setMaster("local[*]");

        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConfig);


		JavaRDD textFileRDD = javaSparkContext.textFile("/home/dharshekthvel/ac/code/scalatrainingintellij/data/auth.csv");
        textFileRDD.foreach(eachLine -> System.out.println(eachLine));



//        javaSparkContext.wholeTextFiles("/home/dharshekthvel/ac/code/scalatrainingintellij/data")
//                .foreach(eachLine -> System.out.println(eachLine._1));

        javaSparkContext.close();
        javaSparkContext.stop();


    }
}


