package br.com.reisproducoes.listadetarefas.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.reisproducoes.listadetarefas.databinding.ActivityMainBinding
import br.com.reisproducoes.listadetarefas.datasource.TarefaDataSource

class MainActivity : AppCompatActivity() {

    private lateinit var binding02: ActivityMainBinding
    private val adapter01 by lazy { ListaTarefaAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding02 = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding02.root)

        binding02.rvTarefas.adapter = adapter01
        updateLista01()

        insertListeners02()
    }

    private fun insertListeners02() {
        binding02.faBotao.setOnClickListener {
            startActivityForResult(Intent(this, AddTarefaActivity::class.java), CRIAR_NOVA_TAREFA)
        }

        adapter01.listenerEditar = {
            val intent = Intent(this, AddTarefaActivity::class.java)
            intent.putExtra(AddTarefaActivity.TAREFA_ID, it.id)
            startActivityForResult(intent, CRIAR_NOVA_TAREFA)
            
        }

        adapter01.listenerDeletar = {
            TarefaDataSource.deleteTarefa(it)
            updateLista01()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CRIAR_NOVA_TAREFA && resultCode == Activity.RESULT_OK) updateLista01()

    }

    private  fun updateLista01(){
        val listao = TarefaDataSource.getLista01()
        binding02.incVazio.emptyEstado.visibility = if(listao.isEmpty()){
           View.VISIBLE
        }else{
            View.GONE
        }
        adapter01.submitList(listao)
    }

    companion object{
        private const val CRIAR_NOVA_TAREFA = 1000
    }

}