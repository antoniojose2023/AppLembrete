package br.com.antoniojose.lembretes.view

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import br.com.antoniojose.lembretes.adapter.AdapterLembretes
import br.com.antoniojose.lembretes.dataSource.DataSource
import br.com.antoniojose.lembretes.databinding.ActivityMainBinding
import br.com.antoniojose.lembretes.model.Lembrete
import br.com.antoniojose.lembretes.view.NovoLembreteActivity.Companion.EXTRA_LEMBRETES

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var adapterLembretes = AdapterLembretes()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startListeners()
        clickButtonCreateNew()
        createRecyclerView()
        updateList()



    }

    fun startListeners(){

            adapterLembretes.listenerEdit = {
                 var intent = Intent(this, NovoLembreteActivity::class.java)
                 intent.putExtra(EXTRA_LEMBRETES, it.id)
                 startActivity(intent)
            }

            adapterLembretes.listenerDelete = {
                 DataSource.delete( it )
                 adapterLembretes.notifyDataSetChanged()
            }

    }


    fun clickButtonCreateNew(){

        binding.ftAction.setOnClickListener {
            startActivity(Intent(this, NovoLembreteActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

    }

    fun createRecyclerView(){

           updateList()
           binding.rvLembretes.apply {
               adapter = adapterLembretes
           }

    }

    fun updateList(){

        var list = DataSource.getLista()
        if(list.isEmpty()){
            binding.layoutEmpty.empytLayout.visibility = View.VISIBLE
            binding.rvLembretes.visibility = View.INVISIBLE
        }else{
            binding.layoutEmpty.empytLayout.visibility = View.INVISIBLE
            binding.rvLembretes.visibility = View.VISIBLE

            adapterLembretes.listaLembretes = list
        }



    }

}