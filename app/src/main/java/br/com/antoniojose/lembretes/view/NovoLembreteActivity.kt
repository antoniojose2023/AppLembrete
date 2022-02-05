package br.com.antoniojose.lembretes.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.antoniojose.lembretes.dataSource.DataSource
import br.com.antoniojose.lembretes.databinding.ActivityNovoLembreteBinding
import br.com.antoniojose.lembretes.extensions.format
import br.com.antoniojose.lembretes.model.Lembrete

import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.lang.String.format
import java.util.*

class NovoLembreteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNovoLembreteBinding
    private var validade = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNovoLembreteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setDataInterface()
        listeners()

    }

    fun listeners(){

        //------------------------toolbar --------------
        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = "Criar Lembrete"


        binding.editData.setOnClickListener {
            var pickerDialog = MaterialDatePicker.Builder.datePicker().build()
            pickerDialog.addOnPositiveButtonClickListener {
                var date = Date(it).format()
                binding.editData.setText(date)
            }
            pickerDialog.show(supportFragmentManager, "100")
        }

        binding.editHora.setOnClickListener {
            var pickerDialogHour = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).build()

            pickerDialogHour.addOnPositiveButtonClickListener {

                var hour = if(pickerDialogHour.hour in 0..9){
                    "0${pickerDialogHour.hour}"
                }else{
                    pickerDialogHour.hour
                }


                var minute = if(pickerDialogHour.minute in 0..9){
                    "0${pickerDialogHour.minute}"
                }else{
                    pickerDialogHour.minute
                }

                binding.editHora.setText( "${hour} : ${minute}")
            }

            pickerDialogHour.show(supportFragmentManager, "200")

        }

        binding.buttoCancel.setOnClickListener {
            closeActivityLembrete()
        }

        binding.buttoNew.setOnClickListener {
            if(!validade){
                var lembrete = Lembrete(
                    titulo = binding.editTitulo.text.toString(),
                    descricao = binding.editDescricao.text.toString(),
                    data = binding.editData.text.toString(),
                    hora = binding.editHora.text.toString(),
                    id = UUID.randomUUID().toString()
                )

                DataSource.setLembrete(lembrete)
                closeActivityLembrete()

            }else{

                val id = intent.getStringExtra(EXTRA_LEMBRETES)
                var list = DataSource.getLista()

                list.find {
                     if(it.id == id){
                             it.titulo = binding.editTitulo.text.toString()
                             it.descricao = binding.editDescricao.text.toString()
                             it.data = binding.editData.text.toString()
                             it.hora = binding.editHora.text.toString()

                         DataSource.setUpdateLembrete(it)
                         closeActivityLembrete()
                     }
                    true
                }

            }

        }

    }

    fun closeActivityLembrete(){
        finish()
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }


    fun setDataInterface(){

        val id = intent.getStringExtra(EXTRA_LEMBRETES)

        if(id != null){

            var list = DataSource.getLista()
            list.find {
                 if(it.id == id){
                     binding.editTitulo.setText( it.titulo)
                     binding.editDescricao.setText( it.descricao)
                     binding.editData.setText( it.data)
                     binding.editHora.setText( it.hora)
                 }
                true
            }

            validade = true
        }

    }


    companion object{

        val EXTRA_LEMBRETES = "lembretes"

    }


}