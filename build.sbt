val GedcomXVersion = "3.9.0"

ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    organization := "org.hyperdiary",
    name := "gedcomx-transformer",
    libraryDependencies ++= Seq(
      "org.gedcomx"   % "gedcomx-fileformat" % GedcomXVersion,
      "org.gedcomx"   % "gedcomx-model"      % GedcomXVersion,
      "com.monovore" %% "decline"            % "2.4.1",
      "org.scalatest" %% "scalatest" % "3.2.17" % Test,
    ),
    assembly / mainClass := Some("org.hyperdiary.gedcomx.GedcomxTransformerApp"),
    assembly / assemblyJarName := "gedcomx-transformer.jar",
    assembly / assemblyMergeStrategy := {
      case PathList("META-INF", "gedcomx.models") => MergeStrategy.discard
      case PathList("META-INF","versions","9","module-info.class") => MergeStrategy.discard
      case "module-info.class" => MergeStrategy.discard

      case x =>
        val oldStrategy = (assembly / assemblyMergeStrategy).value
        oldStrategy(x)
    }
  )

