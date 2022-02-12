package br.com.antoniojose.lembretes.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Lembrete(
    @PrimaryKey
    var id: Int = 0,
    var titulo: String,
    var descricao: String,
    var data: String,
    var hora: String,
): RealmObject(), Serializable {

    constructor(): this(0, "", "", "", "")

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