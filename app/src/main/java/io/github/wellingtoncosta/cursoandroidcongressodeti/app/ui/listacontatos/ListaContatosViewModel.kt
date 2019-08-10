package io.github.wellingtoncosta.cursoandroidcongressodeti.app.ui.listacontatos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.wellingtoncosta.cursoandroidcongressodeti.domain.entity.Contato
import io.github.wellingtoncosta.cursoandroidcongressodeti.domain.repository.ContatoRepository
import io.github.wellingtoncosta.cursoandroidcongressodeti.resources.extension.doInBackground

class ListaContatosViewModel(
    private val repository: ContatoRepository
) : ViewModel() {

    private val _carregando = MutableLiveData<Boolean>()
    private val _erro = MutableLiveData<Throwable>()
    private val _contatos = MutableLiveData<List<Contato>>()
    private val _favoritos = MutableLiveData<List<Contato>>()

    val carregando: LiveData<Boolean> get() = _carregando
    val erro: LiveData<Throwable> get() = _erro
    val contatos: LiveData<List<Contato>> get() = _contatos
    val favoritos: LiveData<List<Contato>> get() = _favoritos

    fun listarContatos() {
        doInBackground {
            try {
                _carregando.postValue(true)

                _contatos.postValue(repository.listarTodos())

            } catch (e: Throwable) {

                _erro.postValue(e)

                e.message?.let { Log.e("listarContatos", it) }

            } finally {
                _carregando.postValue(false)
            }
        }
    }

    fun listarContatosFavoritos() {
        doInBackground {
            try {
                _carregando.postValue(true)

                _favoritos.postValue(repository.listarFavoritos())

            } catch (e: Throwable) {

                _erro.value = e

                e.message?.let { Log.e("listarContatosFavoritos", it) }

            } finally {
                _carregando.postValue(false)
            }
        }
    }

}