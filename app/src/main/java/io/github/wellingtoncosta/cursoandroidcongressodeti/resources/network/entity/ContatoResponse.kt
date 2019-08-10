package io.github.wellingtoncosta.cursoandroidcongressodeti.resources.network.entity

import io.github.wellingtoncosta.cursoandroidcongressodeti.domain.entity.Contato

data class ContatoResponse(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String
)

fun ContatoResponse.toDomain() = Contato(
    id = this.id,
    nome = this.name,
    email = this.email,
    telefone = this.phone
)