package part2dataframes

import org.apache.spark.sql.{SaveMode, SparkSession}

object DsExercise extends App {
  // creating a SparkSession
  val spark = SparkSession.builder()
    .appName("DsExercise")
    .config("spark.master", "local")
    .getOrCreate()


  val movieDF = spark.read.json("src/main/resources/data/movies.json")

  movieDF.write
    .format("csv")
    .option("header", "true")
    .mode(SaveMode.Overwrite)
    .option("sep", "\t")
    .save("src/main/resources/data/movies.csv")

  // Parquet
  movieDF.write.mode(SaveMode.Overwrite).save("src/main/resources/data/movies.parquet")

  // Reading from a remote DB
  val driver = "org.postgresql.Driver"
  val url = "jdbc:postgresql://192.168.99.100:5432/rtjvm"
  val user = "docker"
  val password = "docker"
  // save to DF
  movieDF.write
    .format("jdbc")
    .mode(SaveMode.Overwrite)
    .option("driver", driver)
    .option("url", url)
    .option("user", user)
    .option("password", password)
    .option("dbtable", "public.movies")
    .save()
}
