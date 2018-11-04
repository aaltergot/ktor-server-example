package kse.server.web.dummyAuth

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import kse.server.web.KseApi

class DummyAuthApi: KseApi() {
    override fun Routing.routing() {
        get("dummy-auth") {
            call.respondText("dummy-auth", ContentType.Text.Plain)
        }
    }
}