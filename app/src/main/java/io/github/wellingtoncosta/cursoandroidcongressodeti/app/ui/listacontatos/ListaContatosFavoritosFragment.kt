package io.github.wellingtoncosta.cursoandroidcongressodeti.app.ui.listacontatos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import io.github.wellingtoncosta.cursoandroidcongressodeti.R
import io.github.wellingtoncosta.cursoandroidcongressodeti.databinding.FragmentListaContatosFavoritosBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListaContatosFavoritosFragment : Fragment() {

    private val viewModel by viewModel<ListaContatosViewModel>()

    private lateinit var binding: FragmentListaContatosFavoritosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observarContatosFavoritos()

        observarCarregando()

        observarErro()

        viewModel.listarContatosFavoritos()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(
            inflater, R.layout.fragment_lista_contatos_favoritos, container, false)

        binding.recyclerView.adapter = ListaContatosAdapter()

        binding.swipeContainer.setOnRefreshListener(viewModel::listarContatosFavoritos)

        return binding.root
    }

    private fun observarCarregando() {
        viewModel.carregando.observe(this, Observer {
            binding.swipeContainer.isRefreshing = it ?: false
        })
    }

    private fun observarContatosFavoritos() {
        viewModel.favoritos.observe(this, Observer {
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

}