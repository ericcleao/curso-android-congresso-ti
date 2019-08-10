package io.github.wellingtoncosta.cursoandroidcongressodeti.app

import android.app.Application
import com.github.kittinunf.fuel.core.FuelManager
import io.github.wellingtoncosta.cursoandroidcongressodeti.BuildConfig.API_URL
import io.github.wellingtoncosta.cursoandroidcongressodeti.app.config.databaseModule
import io.github.wellingtoncosta.cursoandroidcongressodeti.app.config.networkModule
import io.github.wellingtoncosta.cursoandroidcongressodeti.app.config.repositoryModule
import io.github.wellingtoncosta.cursoandroidcongressodeti.app.config.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CursoAndroidApp : Application() {

    override fun onCreate() {
        super.onCreate()

        FuelManager.instance.basePath = API_URL

        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidLogger()

            androidContext(this@CursoAndroidApp)

            modules(listOf(
                networkModule,
                databaseModule,
                repositoryModule,
                viewModelModule)
            )
        }
    }

}
