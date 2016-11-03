name := "wartremover-playground"

lazy val example = project.in(file("modules/example"))
  .dependsOn(warts)
  .settings(
    scalaVersion := "2.11.8",
    wartremoverClasspaths += "file://" + baseDirectory.value + "/../warts/target/scala-2.11/warts_2.11-0.1-SNAPSHOT.jar",
    wartremoverErrors += Wart.custom("warts.MyOptionPartial"),
    libraryDependencies += "org.wartremover" % "wartremover_2.11" % "1.1.1"
  )

lazy val warts = project.in(file("modules/warts"))
  .settings(
    scalaVersion := "2.11.8",
    libraryDependencies += "org.wartremover" % "wartremover_2.11" % "1.1.1"
  )


