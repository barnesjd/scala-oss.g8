name := "$name$"

organization := "$organization;format="lower"$"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.9.1"

crossScalaVersions := Seq("2.9.1", "2.9.1-1", "2.9.2", "2.9.3", "2.10.3")

resolvers ++= Seq(
  "sonatype-snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "sonatype-releases"  at "http://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++= {
  Seq(
//    "org.scalatest"  %% "scalatest"   % "1.9.1"  % "test",
//    "org.scalacheck" %% "scalacheck"  % "1.10.1" % "test"
  )
}

scalacOptions <<= scalaVersion map { v: String =>
  val opts = "-deprecation" :: "-unchecked" :: Nil
  if (v.startsWith("2.9.")) opts 
  else opts ++ ("-feature" :: "-language:postfixOps" :: "-language:implicitConversions" :: Nil)
}

// Publishing stuff for sonatype
publishTo <<= version { _.endsWith("SNAPSHOT") match {
    case true  => Some("snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")
    case false => Some("releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
  }
}

credentials += Credentials( file("sonatype.credentials") )

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
        <url>$project_url$</url>
        <licenses>
            <license>
              <name>Apache 2.0 License</name>
              <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
              <distribution>repo</distribution>
            </license>
         </licenses>
         <scm>
            <url>git@github.com:$github_id$/$name;format="norm"$.git</url>
            <connection>scm:git:git@github.com:$github_id$/$name;format="norm"$.git</connection>
         </scm>
         <developers>
            <developer>
              <id>$github_id$</id>
              <name>$developer_name$</name>
              <url>$developer_url$</url>
            </developer>
         </developers>
 )

// OSGi Bundle stuff
osgiSettings

OsgiKeys.bundleSymbolicName := "$package$"

OsgiKeys.exportPackage := Seq("$package$")

OsgiKeys.privatePackage := Seq()

OsgiKeys.bundleActivator := None

