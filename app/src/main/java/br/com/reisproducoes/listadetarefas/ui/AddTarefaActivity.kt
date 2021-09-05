package br.com.reisproducoes.listadetarefas.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.reisproducoes.listadetarefas.databinding.ActivityAddTarefaBinding
import br.com.reisproducoes.listadetarefas.datasource.TarefaDataSource
import br.com.reisproducoes.listadetarefas.extensions.format
import br.com.reisproducoes.listadetarefas.extensions.texto01
import br.com.reisproducoes.listadetarefas.model.Tarefa
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

// : AppCompatActivity() declara que a classe é uma activity
// necessário adicionar essa activity no androidmanifest
class AddTarefaActivity : AppCompatActivity() {

    // lateinit significa que vai ser inicializada depois
    private lateinit var binding01: ActivityAddTarefaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // para essa linha de baixo funcionar, deve adicionar esse codigo no buildgradleapp  buildFeatures{  viewBinding = true }
        binding01 = ActivityAddTarefaBinding.inflate(layoutInflater)
        setContentView(binding01.root)

        // trás de volta preenchido
        if (intent.hasExtra(TAREFA_ID)){
            val tarefaId= intent.getIntExtra(TAREFA_ID, 0)
            TarefaDataSource.findById(tarefaId)?.let {
                binding01.tilTitulo.texto01 = it.titulo
                binding01.tilData.texto01 = it.data
                binding01.tilHora.texto01 = it.hora
            }
        }

        insertListeners01()

    }

    private fun insertListeners01() {
        binding01.tilData.editText?.setOnClickListener {
            // usamos o log para ver se funcionava o clic Log.e("TAG", "insertListeners01: ", )
            val datePicker01 = MaterialDatePicker.Builder.datePicker().build()
            datePicker01.addOnPositiveButtonClickListener {
                // timezone e offset é para pegar a data corretamente
                val timeZone01 = TimeZone.getDefault()
                val offset01 = timeZone01.getOffset(Date().time) * -1
                // para esse format funcionar teve que criar um pacote extensions e um arquivo AppExtencao
                binding01.tilData.texto01 = Date(it + offset01).format()
            }
            datePicker01.show(supportFragmentManager, "DATA_PICKER01_TAG")
        }

        binding01.tilHora.editText?.setOnClickListener {
            val timePicker01 = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

            timePicker01.addOnPositiveButtonClickListener {
                val minuto =if (timePicker01.minute in 0..9) "0${timePicker01.minute}" else timePicker01.minute
                val hora = if (timePicker01.hour in 0..9) "0${timePicker01.hour}" else timePicker01.hour

                binding01.tilHora.texto01 = "$hora:$minuto"
            }
            timePicker01.show(supportFragmentManager, null)
        }

        binding01.btnCancelar.setOnClickListener {
            finish()
        }

        binding01.btnNovaTarefa.setOnClickListener {
            val tarefa = Tarefa(
                titulo = binding01.tilTitulo.texto01,
                data =  binding01.tilData.texto01,
                hora = binding01.tilHora.texto01,
                id = intent.getIntExtra(TAREFA_ID, 0)
            )
            TarefaDataSource.insertTarefa01(tarefa)

            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    companion object {
        const val TAREFA_ID = "tarefa_id"
    }

}