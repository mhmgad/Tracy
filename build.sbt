name := """fact-checking-demo"""

version := "1.0-SNAPSHOT"

//lazy val api= RootProject(file("../api"))
//externalPom()
lazy val root = (project in file(".")).enablePlugins(PlayJava)
//.dependsOn(api)

scalaVersion := "2.12.2"

resolvers += "Local Maven" at Path.userHome.asFile.toURI.toURL + ".m2/repository"
resolvers += Resolver.url("utils-repo", url("http://people.mpi-inf.mpg.de/~gadelrab/downloads/repo/"))
//unmanagedBase := baseDirectory.value / "libs"

libraryDependencies += guice

libraryDependencies += "factchecking" % "api" % "1.0"
libraryDependencies += "factchecking" % "client" % "1.0"
libraryDependencies += "de.mpii.exfakt" % "utils" % "1.0-SNAPSHOT"
libraryDependencies += "de.mpg.mpi-inf" % "basics3" % "1.1"


// Test Database
libraryDependencies += "com.h2database" % "h2" % "1.4.194"

//libraryDependencies += "org.webjars" % "bootstrap" % "4.0.0-beta-1"
libraryDependencies += "org.webjars" % "bootstrap" % "4.1.3"
libraryDependencies += "org.webjars" % "font-awesome" % "5.6.1"
libraryDependencies += "org.webjars" % "jquery" % "3.3.1-1"
//libraryDependencies += "org.webjars" % "font-awesome" % "5.0.2"
// https://mvnrepository.com/artifact/org.webjars.bower/popper.js
libraryDependencies += "org.webjars.bower" % "popper.js" % "1.14.4"
// https://mvnrepository.com/artifact/org.webjars.bower/typeahead.js
//libraryDependencies += "org.webjars.bower" % "typeahead.js" % "0.11.1" exclude("org.webjars.bower", "jquery")



// Testing libraries for dealing with CompletionStage...
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test

libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.11.1"
libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.11.1"


//// Make verbose tests
//testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))
