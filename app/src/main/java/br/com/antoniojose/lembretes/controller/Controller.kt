package br.com.antoniojose.lembretes.controller

import android.content.Context
import android.util.Log
import br.com.antoniojose.lembretes.dataSource.AppDatabase
import br.com.antoniojose.lembretes.model.Lembrete
import io.realm.exceptions.RealmException
import kotlin.math.absoluteValue

class Controller(context: Context) {

    private val realm = AppDatabase().getInstanceDatabase( context )

    fun save(lembrete: Lembrete): Boolean{
         var validate = false

         try{
             var id: Number? = realm.where(Lembrete::class.java).max( "id" )
             var autoIncremente = if(id == null) 1 else id.toInt().absoluteValue + 1

             lembrete.id = autoIncremente

             realm.beginTransaction()
             realm.copyToRealm( lembrete )
             realm.commitTransaction()

             validate = true

         } catch (ex: RealmException){
             Log.i("info", "save: ${ex.message}")
         }finally {
             realm.close()
         }

        return validate
    }

    fun update(lembrete: Lembrete): Boolean{
        var validate = false


        try {
            var lembreteObjectBD = realm.where(lembrete::class.java).equalTo("id", lembrete.id).findFirst()

            realm.beginTransaction()
            lembreteObjectBD?.titulo = lembrete.titulo
            lembreteObjectBD?.descricao = lembrete.descricao
            lembreteObjectBD?.data = lembrete.data
            lembreteObjectBD?.hora = lembrete.hora

            realm.commitTransaction()
            validate = true

        }catch (ex: RealmException){
            Log.i("info", "update: ${ex.message}")
        }finally {
            realm.close()
        }

        return validate
    }

    fun delete(lembrete: Lembrete): Boolean{
        var validate = false

        try{

            realm.beginTransaction()
            var results = realm.where(Lembrete::class.java).equalTo("id", lembrete.id).findAll()
            validate = results.deleteAllFromRealm()
            realm.commitTransaction()


        }catch (ex: RealmException){
            Log.i("info", "delete: ${ex.message}")
        }

        return validate
    }

    fun getListLembretes(): ArrayList<Lembrete>{
        var list = arrayListOf<Lembrete>()

        try{
              var results = realm.where(Lembrete::class.java).findAll()
              list = realm.copyFromRealm( results ) as ArrayList<Lembrete>

        }catch (ex: RealmException){
            Log.i("info", "getList: ${ex.message}")
        }

        return list
    }

}