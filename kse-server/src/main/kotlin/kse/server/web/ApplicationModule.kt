package kse.server.web

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.routing.Routing
import io.ktor.routing.routing

class ApplicationModule(
    private val apis: List<KseApi>
) {
    fun setup(app: Application) = app.apply {
        install(Authentication) {

        }
        routing {
            apis.forEach { it.setupRouting(this) }
        }
    }
}

abstract class KseApi {
    protected abstract fun Routing.routing()
    fun setupRouting(routing: Routing) = routing.apply { routing() }
}