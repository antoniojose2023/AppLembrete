package br.com.antoniojose.lembretes.dataSource

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

class AppDatabase{
    private val VERSION_BD = 1L
    private val NAME_BD = "Lembretes.realm"

    fun getInstanceDatabase(context: Context): Realm{
        Realm.init( context )

        var realmConfiguration = RealmConfiguration.Builder()
                                .name(NAME_BD)
                                .schemaVersion(VERSION_BD)
                                .allowWritesOnUiThread(true)
                                //.allowQueriesOnUiThread(true)
                                .build()

        return Realm.getInstance( realmConfiguration )
    }
}