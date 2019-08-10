package io.github.wellingtoncosta.cursoandroidcongressodeti.resources.repository

import io.github.wellingtoncosta.cursoandroidcongressodeti.domain.entity.Contato
import io.github.wellingtoncosta.cursoandroidcongressodeti.domain.repository.ContatoRepository
import io.github.wellingtoncosta.cursoandroidcongressodeti.resources.database.dao.ContatoFavoritoDao
import io.github.wellingtoncosta.cursoandroidcongressodeti.resources.database.entity.toDomain
import io.github.wellingtoncosta.cursoandroidcongressodeti.resources.database.entity.toEntity
import io.github.wellingtoncosta.cursoandroidcongressodeti.resources.extension.runAsync
import io.github.wellingtoncosta.cursoandroidcongressodeti.resources.network.api.ContatoApi
import io.github.wellingtoncosta.cursoandroidcongressodeti.resources.network.entity.toDomain

class ContatoRepositoryImp(
    private val api: ContatoApi,
    private val dao: ContatoFavoritoDao
) : ContatoRepository {

    override fun listarTodos(): List<Contato> {
        return runAsync {
            api.listarTodos().map { it.toDomain() }
        }
    }

    override fun listarFavoritos(): List<Contato> {
        return runAsync {
            dao.listarTodos().map { it.toDomain() }
        }
    }

    override fun buscarPorId(contatoId: Int): Contato? {
        return runAsync {
            api.buscarPorId(contatoId)?.toDomain()
        }
    }

    override fun favoritar(contato: Contato): Contato {
        return runAsync {
            val favorito = dao.buscarPorExternoId(contato.id)

            if(favorito != null) {
                dao.deletar(favorito)

                contato.copy(favoritoId = null)
            } else {
                dao.inserir(contato.toEntity()).let {
                    contato.copy(favoritoId = it.toInt())
                }
            }
        }
    }

}
