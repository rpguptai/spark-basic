package part2dataframes

import org.apache.spark.sql.SparkSession


object DfExercise extends App {

  // creating a SparkSession
  val spark = SparkSession.builder()
    .appName("DfExercise")
    .config("spark.master", "local")
    .getOrCreate()

  // create DF from tuples
  val mobile = Seq(
    ("1995","IPHONE 6 PLUS",5.5,"USA"),
    ("1996","IPHONE 7 PLUS",5.5,"USA"),
    ("1997","IPHONE 8 PLUS",6.0,"INDIA"),
    ("1998","IPHONE 10",6.0,"USA")
  )

  import spark.implicits._
  val mobileDF = mobile.toDF("YEAR", "MODEL", "Screen Size", "Country")

  // reading a DF
  val movieDF = spark.read
    .format("json")
    .option("inferSchema", "true")
    .load("src/main/resources/data/movies.json")

  movieDF.show(5)
  movieDF.printSchema()
  println(s"The Movies DF has ${movieDF.count()} rows")
}
