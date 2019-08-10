package io.github.wellingtoncosta.cursoandroidcongressodeti.app.ui.favoritacontato

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.wellingtoncosta.cursoandroidcongressodeti.domain.entity.Contato
import io.github.wellingtoncosta.cursoandroidcongressodeti.domain.repository.ContatoRepository
import io.github.wellingtoncosta.cursoandroidcongressodeti.resources.extension.doInBackground

class FavoritarContatoViewModel(
    private val repository: ContatoRepository
) : ViewModel() {

    private val _carregando = MutableLiveData<Boolean>()
    private val _erro = MutableLiveData<Throwable>()
    private val _contato = MutableLiveData<Contato>()

    val carregando: LiveData<Boolean> get() = _carregando
    val erro: LiveData<Throwable> get() = _erro
    val contato: LiveData<Contato> get() = _contato

    fun buscarContatoPorId(contatoId: Int) {
        doInBackground {
            try {
                _carregando.postValue(true)

                _contato.postValue(repository.buscarPorId(contatoId))

            } catch (e: Throwable) {

                _erro.postValue(e)

                e.message?.let { Log.e("buscarContatoPorId", it) }

            } finally {
                _carregando.postValue(false)
            }
        }
    }

    fun favoritarContato(contato: Contato) {
        doInBackground {
            try {
                _carregando.postValue(true)

                _contato.postValue(repository.favoritar(contato))

            } catch (e: Throwable) {

                _erro.postValue(e)

                e.message?.let { Log.e("favoritarContato", it) }

            } finally {
                _carregando.postValue(false)
            }
        }
    }

}