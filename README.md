# Configuring a Spark application with Typesafe config (example application)

Example code for my blog post ["Configuring Spark application with Typesafe config"](http://florentforest.xyz/2019/01/07/configuring-spark-applications-with-typesafe-config.html).

## Basic instructions

Build assembly JAR:

```shell
sbt assembly
```

Run (local mode):

```shell
spark-submit --master local[*] --class xyz.florentforest.sparkconfigexample.ConfigWordCount --files wordcount.conf --driver-java-options -Dconfig.file=wordcount.conf sparkconfigexample-assembly-1.0.jar 
```
