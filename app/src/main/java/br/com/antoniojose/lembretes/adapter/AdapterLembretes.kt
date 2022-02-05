package br.com.antoniojose.lembretes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.antoniojose.lembretes.R
import br.com.antoniojose.lembretes.model.Lembrete

class AdapterLembretes(): RecyclerView.Adapter<AdapterLembretes.ViewHolderLembretes>() {

    var listaLembretes: ArrayList<Lembrete> = arrayListOf()

    var listenerEdit : (Lembrete) -> Unit = {}
    var listenerDelete : (Lembrete) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderLembretes {
          var view = LayoutInflater.from(parent.context).inflate(R.layout.item_lembrete, parent, false)
          return ViewHolderLembretes( view )
    }

    override fun onBindViewHolder(holder: ViewHolderLembretes, position: Int) {
            var lembrete = listaLembretes[position]
            holder.bind( lembrete )

    }

    override fun getItemCount() = listaLembretes.size

    inner class ViewHolderLembretes(itemView: View): RecyclerView.ViewHolder(itemView){

        var titulo: TextView = itemView.findViewById(R.id.tv_titulo_item_lembrete)
        var descricao: TextView = itemView.findViewById(R.id.tv_descricao_item_lembrete)
        var data : TextView = itemView.findViewById(R.id.tv_data_item_lembrete)

        var iv_opcoes: ImageView = itemView.findViewById(R.id.iv_opcoes)



        fun bind(lembrete: Lembrete){
            titulo.text = lembrete.titulo
            descricao.text = lembrete.descricao
            data.text = "${lembrete.data} - ${lembrete.hora}"

            iv_opcoes.setOnClickListener {

                    var popMenu = PopupMenu(iv_opcoes.context, iv_opcoes)
                    popMenu.menuInflater.inflate(R.menu.menu, popMenu.menu)

                    popMenu.setOnMenuItemClickListener {
                        when (it.itemId){
                            R.id.menu_edit -> listenerEdit(lembrete)
                            R.id.menu_delete -> listenerDelete(lembrete)

                        }
                        return@setOnMenuItemClickListener true
                    }

                    popMenu.show()
            }
        }

    }
}