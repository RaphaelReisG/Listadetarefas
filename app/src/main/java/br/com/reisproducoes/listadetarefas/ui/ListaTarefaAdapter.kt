package br.com.reisproducoes.listadetarefas.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.reisproducoes.listadetarefas.R
import br.com.reisproducoes.listadetarefas.databinding.ItemTarefaBinding
import br.com.reisproducoes.listadetarefas.model.Tarefa

class ListaTarefaAdapter : ListAdapter<Tarefa, ListaTarefaAdapter.TarefaViewHolder>(DiffCallback01()) {

    var listenerEditar : (Tarefa) -> Unit = {}
    var listenerDeletar : (Tarefa) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val inflater01 = LayoutInflater.from(parent.context)
        val binding04 = ItemTarefaBinding.inflate(inflater01, parent, false)
        return TarefaViewHolder(binding04)
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        holder.bind01(getItem(position))
    }

    inner class TarefaViewHolder(
        private val binding03: ItemTarefaBinding
    ) : RecyclerView.ViewHolder(binding03.root) {

        fun bind01(item: Tarefa) {
            binding03.tvTitulo02.text = item.titulo
            binding03.tvData.text = "${item.data} ${item.hora}"
            binding03.ivMais.setOnClickListener {
                showPopup01(item)
            }
        }
        private fun showPopup01(item: Tarefa) {
            val ivMais01 = binding03.ivMais
            val popupMenu01 = PopupMenu(ivMais01.context, ivMais01)
            //                              R = Resources
            popupMenu01.menuInflater.inflate(R.menu.popup_menu_top, popupMenu01.menu)
            popupMenu01.setOnMenuItemClickListener {
                when (it.itemId){
                    R.id.action_editar -> listenerEditar(item)
                    R.id.action_deletar -> listenerDeletar(item)
                }
                return@setOnMenuItemClickListener true
            }
            popupMenu01.show()
        }
    }
}

class  DiffCallback01 : DiffUtil.ItemCallback<Tarefa>(){
    override fun areItemsTheSame(oldItem: Tarefa, newItem: Tarefa) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Tarefa, newItem: Tarefa) = oldItem.id == newItem.id
}