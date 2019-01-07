package xyz.florentforest.sparkconfigexample

import com.typesafe.config.Config
import scala.collection.JavaConverters._

/**
 * @author FlorentF9
 * Settings class for configuration.
 */
class Settings(config: Config) extends Serializable {

    val inputFile = config.getString("file.input")
    val outputFile = config.getString("file.output")

    val minCount = config.getInt("minCount")
    val stopWords = config.getStringList("stopWords").asScala

}
