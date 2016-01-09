name := """twi"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  javaWs,
  "mysql" % "mysql-connector-java" % "5.1.18",
  evolutions,
  javaJpa,
  "org.mindrot" % "jbcrypt" % "0.3m"
)

playEbeanModels in Compile := Seq("models.*")

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

fork in run := true
