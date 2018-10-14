buildscript {
    repositories {
        maven(url = "http://kotlin.bintray.com/kotlin-eap")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.0-rc-146")
    }
}

apply(plugin = "kotlin-platform-jvm")

repositories {
    jcenter()
    maven(url = "http://kotlin.bintray.com/kotlin-eap")
}

dependencies {
    add("compile", kotlin("stdlib"))
}
