package br.com.antoniojose.lembretes.view

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import br.com.antoniojose.lembretes.adapter.AdapterLembretes
import br.com.antoniojose.lembretes.controller.Controller
import br.com.antoniojose.lembretes.dataSource.DataSource
import br.com.antoniojose.lembretes.databinding.ActivityMainBinding
import br.com.antoniojose.lembretes.model.Lembrete
import br.com.antoniojose.lembretes.view.NovoLembreteActivity.Companion.EXTRA_LEMBRETES

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var adapterLembretes = AdapterLembretes()
    private lateinit var controller: Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        controller = Controller(this)

        clickButtonCreateNew()
        createRecyclerView()
        updateList()
        startListeners()



    }

    fun startListeners(){

            adapterLembretes.listenerEdit = {
                 var intent = Intent(this, NovoLembreteActivity::class.java)
                 intent.putExtra(EXTRA_LEMBRETES, it)
                 startActivity(intent)
            }

            adapterLembretes.listenerDelete = {
                 controller.delete( it )
                 adapterLembretes.notifyDataSetChanged()
                 finishActivity(100)
                 startActivity(intent)
                 overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
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

        var list = controller.getListLembretes()
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