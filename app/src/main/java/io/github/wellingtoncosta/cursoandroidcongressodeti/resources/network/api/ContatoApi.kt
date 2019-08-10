package io.github.wellingtoncosta.cursoandroidcongressodeti.resources.network.api

import io.github.wellingtoncosta.cursoandroidcongressodeti.resources.network.entity.ContatoResponse

interface ContatoApi {

    fun listarTodos(): List<ContatoResponse>

    fun buscarPorId(contatoId: Int): ContatoResponse?

}
