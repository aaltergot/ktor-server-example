import kse.prj.Deps
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        jcenter()
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "http://kotlin.bintray.com/kotlin-eap")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${extra["kotlin_version"]}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${extra["kotlin_version"]}")
    }
}

apply(plugin = "application")
apply(plugin = "kotlin")
apply(plugin = "kotlinx-serialization")

configure<ApplicationPluginConvention> {
    mainClassName = "kse.server.ApplicationKt"
}

dependencies {
    add("compile", kotlin("stdlib"))
    add("compile", kotlin("reflect"))
    add("testCompile", kotlin("test"))
    add("testCompile", kotlin("test-junit"))
    add("compile", Deps.kotlinLogging)
    add("compile", Deps.logbackClassic)
    add("compile", Deps.picocli)
    add("compile", Deps.apacheCommonsLang)
    add("compile", Deps.apacheCommonsText)
    add("compile", Deps.ktorNetty)
    add("compile", Deps.serializationRuntime)
}
