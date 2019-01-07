package xyz.florentforest.sparkconfigexample

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, not, lower, desc}
import com.typesafe.config.{Config, ConfigFactory}

/**
 * @author FlorentF9
 * Example application with configuration file.
 */
object ConfigWordCount {
    def main(args: Array[String]) {

        // Load configuration into Settings class
        val conf: Config = ConfigFactory.load()
        val settings: Settings = new Settings(conf)

        val spark: SparkSession = SparkSession.builder()
                                              .appName("Word Count (config)")
                                              .getOrCreate()
        import spark.implicits._

        // Business logic
        val document = spark.read.textFile(settings.inputFile)
        val result   = document.flatMap(_.split(" "))
                               .filter(not(lower(col("value")).isin(settings.stopWords: _*)))
                               .groupBy("value")
                               .count()
                               .filter(col("count") >= settings.minCount)
                               .orderBy(desc("count"))
        result.coalesce(1).write.csv(settings.outputFile)

        spark.stop()

    }
}
