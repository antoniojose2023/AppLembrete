package br.com.antoniojose.lembretes.dataSource

import br.com.antoniojose.lembretes.model.Lembrete

object DataSource {

    private var list = arrayListOf<Lembrete>()

    fun getLista() = list

    fun setLembrete(lembrete: Lembrete){

          list.add( lembrete )
    }

    fun setUpdateLembrete(lembrete: Lembrete){

        list.find {
            if(it.id == lembrete.id){
                   list.remove( lembrete )
                   list.add(lembrete)
            }
            true
        }

    }

    fun delete(lembrete: Lembrete){
        list.remove(lembrete)
    }

}