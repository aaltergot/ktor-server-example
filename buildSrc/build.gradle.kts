buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.0")
    }
}

repositories {
    jcenter()
}

apply(plugin = "kotlin")

dependencies {
    add("compile", kotlin("stdlib"))
}
