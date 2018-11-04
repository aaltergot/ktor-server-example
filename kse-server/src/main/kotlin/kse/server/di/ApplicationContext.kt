package kse.server.di

import com.typesafe.config.Config
import kse.server.web.ApplicationModule
import kse.server.web.dummy.DummyApi
import kse.server.web.dummyAuth.DummyAuthApi
import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.allInstances
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class ApplicationContext(val applicationConfig: Config) {
    private val kodein = Kodein {
        bind<ApplicationModule>() with singleton { ApplicationModule(allInstances()) }
        // APIs
        bind<DummyApi>() with singleton { DummyApi() }
        bind<DummyAuthApi>() with singleton { DummyAuthApi() }
    }

    fun applicationModule(): ApplicationModule = kodein.direct.instance()
}