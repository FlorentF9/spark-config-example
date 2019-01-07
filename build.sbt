name := "sparkconfigexample"
organization := "xyz.florentforest"
version := "1.0"
scalaVersion := "2.11.8"
libraryDependencies ++= Seq("org.apache.spark" %% "spark-sql" % "2.3.0" % "provided",
                            "com.typesafe"      % "config"    % "1.3.2")
