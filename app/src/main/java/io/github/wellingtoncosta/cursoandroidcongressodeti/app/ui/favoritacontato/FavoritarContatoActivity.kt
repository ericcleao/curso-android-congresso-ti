package io.github.wellingtoncosta.cursoandroidcongressodeti.app.ui.favoritacontato

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import io.github.wellingtoncosta.cursoandroidcongressodeti.R
import io.github.wellingtoncosta.cursoandroidcongressodeti.app.ui.listacontatos.ListaContatosFragment.Companion.CONTATO_ID
import io.github.wellingtoncosta.cursoandroidcongressodeti.databinding.ActivityFavoritarContatoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritarContatoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritarContatoBinding

    private val viewModel by viewModel<FavoritarContatoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.extras?.let {
            if(intent.hasExtra(CONTATO_ID)) {
                viewModel.buscarContatoPorId(it.getInt(CONTATO_ID))
            }
        }

        configurarBinding()

        configurarToolbar()

        observarContato()

        observarErro()
    }

    private fun configurarBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favoritar_contato)

        binding.lifecycleOwner = this

        binding.viewModel = this.viewModel
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish() }
        return super.onOptionsItemSelected(item)
    }

    private fun configurarToolbar() {
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun observarContato() {
        viewModel.contato.observe(this, Observer {
            val iconResId = if(it.favoritoId != null) {
                R.drawable.ic_full_heart
            } else {
                R.drawable.ic_outline_heart
            }

            binding.buttonFavoritarContato.setImageDrawable(
                ContextCompat.getDrawable(this, iconResId)
            )
        })
    }

    private fun observarErro() {
        viewModel.erro.observe(this, Observer {
            Toast.makeText(
                this, R.string.falha_carregar_favoritar_contato, Toast.LENGTH_LONG
            ).show()
        })
    }

}
