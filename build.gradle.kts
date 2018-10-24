allprojects {
    group = "com.github.aaltergot"
    version = "0.1.0"
    repositories {
        jcenter()
        maven(url = "http://kotlin.bintray.com/kotlin-eap")
        maven(url = "https://dl.bintray.com/kotlin/ktor")
        maven(url = "https://kotlin.bintray.com/kotlinx")
    }
}
