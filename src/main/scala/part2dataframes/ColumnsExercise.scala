package part2dataframes

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, column, expr}
object ColumnsExercise extends App {

  val spark = SparkSession.builder()
    .appName("DF Columns and Expressions")
    .config("spark.master", "local")
    .getOrCreate()

  val moviesDF = spark.read
    .option("inferSchema", "true")
    .json("src/main/resources/data/movies.json")

  moviesDF.printSchema()

  // select with plain column names
  val moviesDFTwoCols = moviesDF.select("Rotten_Tomatoes_Rating", "IMDB_Rating")
  moviesDFTwoCols.show(5)

  val moviesProfitDF = moviesDF.select("Title", "US_Gross", "Worldwide_Gross")
    .withColumn("Total_Gross", col("US_Gross") + col("Worldwide_Gross"))

  val comedyMovieDF = moviesDF.filter("Major_Genre = 'Comedy' and IMDB_Rating > 6")
  comedyMovieDF.show()

}
