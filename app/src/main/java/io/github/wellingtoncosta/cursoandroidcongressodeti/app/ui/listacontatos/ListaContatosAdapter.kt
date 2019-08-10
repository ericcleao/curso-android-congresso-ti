package io.github.wellingtoncosta.cursoandroidcongressodeti.app.ui.listacontatos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.wellingtoncosta.cursoandroidcongressodeti.R
import io.github.wellingtoncosta.cursoandroidcongressodeti.databinding.ListaContatoItemBinding
import io.github.wellingtoncosta.cursoandroidcongressodeti.domain.entity.Contato

class ListaContatosAdapter(
    private val onItemClick: (Int) -> Unit = { }
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val contatos = mutableListOf<Contato>()

    fun atualizarContatos(contatos: List<Contato>) {
        this.contatos.clear()

        this.contatos.addAll(contatos)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListaContatosViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.lista_contato_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val contato = contatos[position]
        val binding = (holder as ListaContatosViewHolder).binding

        binding.contato = contato
        binding.layout.setOnClickListener { onItemClick(contato.id) }
    }

    override fun getItemCount() = contatos.size

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)

        (holder as ListaContatosViewHolder).binding.layout.setOnClickListener(null)
    }

}

private class ListaContatosViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding: ListaContatoItemBinding = ListaContatoItemBinding.bind(view)

}
