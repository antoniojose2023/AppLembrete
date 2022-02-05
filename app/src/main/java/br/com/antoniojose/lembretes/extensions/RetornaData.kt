package br.com.antoniojose.lembretes.extensions

import java.text.SimpleDateFormat
import java.util.*


    fun Date.format(): String{
        var locale = Locale("pt", "BR")
        return SimpleDateFormat("dd/MM/yyyy", locale).format(this)
    }

