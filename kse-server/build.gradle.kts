import kse.prj.Deps

buildscript {
    repositories {
        maven(url = "http://kotlin.bintray.com/kotlin-eap")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${extra["kotlin_version"]}")
    }
}

apply(plugin = "kotlin-platform-jvm")

dependencies {
    add("compile", kotlin("stdlib"))
    add("compile", kotlin("reflect"))
    add("compile", Deps.kotlinLogging)
    add("compile", Deps.logbackClassic)
}
