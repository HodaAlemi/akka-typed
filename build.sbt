name := "akkaTyped"

version := "0.1"

scalaVersion := "2.13.8"

val akkaHttpVersion= "10.1.8"
val akkaVersion= "2.6.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-cluster-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,

  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,

  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "org.slf4j" % "slf4j-api" % "1.7.30",
  "ch.qos.logback" % "logback-classic" % "1.3.0-alpha5"
)