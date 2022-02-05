package br.com.antoniojose.lembretes.model

import java.io.Serializable

class Lembrete(
    var id: String,
    var titulo: String,
    var descricao: String,
    var data: String,
    var hora: String,
):Serializable {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Lembrete

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}