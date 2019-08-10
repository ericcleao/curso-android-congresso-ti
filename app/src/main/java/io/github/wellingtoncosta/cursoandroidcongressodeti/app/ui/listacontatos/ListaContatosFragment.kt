package io.github.wellingtoncosta.cursoandroidcongressodeti.app.ui.listacontatos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import io.github.wellingtoncosta.cursoandroidcongressodeti.R
import io.github.wellingtoncosta.cursoandroidcongressodeti.app.ui.favoritacontato.FavoritarContatoActivity
import io.github.wellingtoncosta.cursoandroidcongressodeti.databinding.FragmentListaContatosBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListaContatosFragment : Fragment() {

    private val viewModel by viewModel<ListaContatosViewModel>()

    private lateinit var binding: FragmentListaContatosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observarContatos()

        observarCarregando()

        observarErro()

        viewModel.listarContatos()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(
            inflater, R.layout.fragment_lista_contatos, container, false)

        binding.recyclerView.adapter = ListaContatosAdapter(::irParaTelaFavoritarContato)

        binding.swipeContainer.setOnRefreshListener(viewModel::listarContatos)

        return binding.root
    }

    private fun observarCarregando() {
        viewModel.carregando.observe(this, Observer {
            binding.swipeContainer.isRefreshing = it ?: false
        })
    }

    private fun observarContatos() {
        viewModel.contatos.observe(this, Observer {
            (binding.recyclerView.adapter as ListaContatosAdapter)
                .atualizarContatos(it)
        })
    }

    private fun observarErro() {
        viewModel.erro.observe(this, Observer {
            Toast.makeText(
                context, R.string.falha_carregar_contatos, Toast.LENGTH_LONG
            ).show()
        })
    }

    private fun irParaTelaFavoritarContato(contatoId: Int) {
        Log.d("CONTATO_SELECIONADO", contatoId.toString())

        val intent = Intent(activity, FavoritarContatoActivity::class.java)

        intent.putExtra(CONTATO_ID, contatoId)

        startActivity(intent)
    }

    companion object {
        const val CONTATO_ID = "contato-id"
    }

}