package xyz.florentforest.sparkconfigexample

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, not, lower, desc}
import org.apache.log4j.{LogManager, Level}

/**
 * @author FlorentF9
 * Example application with command-line arguments.
 */
object CmdWordCount {
    def main(args: Array[String]) {

        // Read command-line arguments
        // (ok, you could do better and write a dedicated function or use an external library for argument parsing)
        val inputFile: String = args(0)
        val outputFile: String = args(1)
        val minCount: Int = args(2).toInt
        val stopWords: Array[String] = args(3).stripPrefix("[").stripSuffix("]")
                                              .split(",")
                                              .map(_.stripPrefix("\"").stripSuffix("\""))

        val spark: SparkSession = SparkSession.builder()
                                              .appName("Word Count (cmd)")
                                              .getOrCreate()
        import spark.implicits._

        // Business logic
        val document = spark.read.textFile(inputFile)
        val result   = document.flatMap(_.split(" "))
                               .filter(not(lower(col("value")).isin(stopWords: _*)))
                               .groupBy("value")
                               .count()
                               .filter(col("count") >= minCount)
                               .orderBy(desc("count"))
        result.coalesce(1).write.csv(outputFile)

        spark.stop()

    }
}
