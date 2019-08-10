package io.github.wellingtoncosta.cursoandroidcongressodeti.app.ui.listacontatos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import io.github.wellingtoncosta.cursoandroidcongressodeti.R
import io.github.wellingtoncosta.cursoandroidcongressodeti.databinding.ActivityListaContatosBinding

class ListaContatosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaContatosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_lista_contatos)

        configurarToolbar()

        configurarTabs()
    }

    private fun configurarToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }

    private fun configurarTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(
            ListaContatosFragment(),
            getString(R.string.lista_contatos_tab)
        )

        adapter.addFragment(
            ListaContatosFavoritosFragment(),
            getString(R.string.lista_contatos_favoritos_tab)
        )

        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

}
