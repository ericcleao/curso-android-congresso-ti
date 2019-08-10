package io.github.wellingtoncosta.cursoandroidcongressodeti.app.config

import androidx.room.Room
import io.github.wellingtoncosta.cursoandroidcongressodeti.app.ui.favoritacontato.FavoritarContatoViewModel
import io.github.wellingtoncosta.cursoandroidcongressodeti.app.ui.listacontatos.ListaContatosViewModel
import io.github.wellingtoncosta.cursoandroidcongressodeti.domain.repository.ContatoRepository
import io.github.wellingtoncosta.cursoandroidcongressodeti.resources.database.AppDatabase
import io.github.wellingtoncosta.cursoandroidcongressodeti.resources.database.AppDatabase.Companion.DATABASE_NAME
import io.github.wellingtoncosta.cursoandroidcongressodeti.resources.network.api.ContatoApi
import io.github.wellingtoncosta.cursoandroidcongressodeti.resources.network.api.fuel.ContatoFuelApi
import io.github.wellingtoncosta.cursoandroidcongressodeti.resources.repository.ContatoRepositoryImp
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single<ContatoApi> { ContatoFuelApi() }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    single { get<AppDatabase>().contatoFavoritoDao() }
}

val repositoryModule = module {
    single<ContatoRepository> { ContatoRepositoryImp(get(), get()) }
}

val viewModelModule = module {
    viewModel { ListaContatosViewModel(get()) }
    viewModel { FavoritarContatoViewModel(get()) }
}