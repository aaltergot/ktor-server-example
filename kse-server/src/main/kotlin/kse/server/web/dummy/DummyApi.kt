package kse.server.web.dummy

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import kse.server.web.KseApi

class DummyApi: KseApi() {

    override fun Routing.routing() {
        get("/dummy") {
            call.respondText("dummy", ContentType.Text.Plain)
        }
    }

}