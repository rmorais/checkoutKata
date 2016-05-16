name := """checkoutKata"""

version := "1.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
//libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.8" % "test")

scalacOptions in Test ++= Seq("-Yrangepos")


