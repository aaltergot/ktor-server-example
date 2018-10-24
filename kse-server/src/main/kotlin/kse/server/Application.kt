package kse.server

import com.typesafe.config.Config
import io.ktor.config.HoconApplicationConfig
import io.ktor.server.engine.applicationEngineEnvironment
import io.ktor.server.engine.connector
import io.ktor.server.engine.embeddedServer
import io.ktor.server.engine.loadCommonConfiguration
import io.ktor.server.netty.DevelopmentEngine
import io.ktor.server.netty.Netty
import kse.server.cli.KseCli
import mu.KotlinLogging
import kse.server.web.kseServer
import java.net.URL

fun main(args: Array<String>) {
    KseCli(
        out = System.out,
        defaultConfigUrl = Application::defaultConfigUrl,
        runServer = Application::runServer
    ).processArgs(args)
}

object Application {

    private val logger = KotlinLogging.logger("Kse")

    val defaultConfigUrl: URL = ClassLoader.getSystemClassLoader().getResource("application.conf")

    @Suppress("EXPERIMENTAL_API_USAGE")
    fun runServer(cfg: Config) {
        embeddedServer(
            factory = Netty,
            environment = applicationEngineEnvironment {
                // see io.ktor.server.engine.CommandLine
                log = logger
                connector {
                    host = cfg.getString("ktor.deployment.host")
                    port = cfg.getInt("ktor.deployment.port")
                }
                module { kseServer() }
            }
        ).start()
    }
}
