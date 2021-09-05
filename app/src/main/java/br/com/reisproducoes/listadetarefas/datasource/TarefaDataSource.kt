package br.com.reisproducoes.listadetarefas.datasource

import br.com.reisproducoes.listadetarefas.model.Tarefa

object TarefaDataSource{
    private val lista01 = arrayListOf<Tarefa>()

    fun getLista01() = lista01.toList()

    fun insertTarefa01(tarefa: Tarefa) {
        if(tarefa.id == 0){
            lista01.add(tarefa.copy(id = lista01.size + 1))
        }
        else{
            lista01.remove(tarefa)
            lista01.add(tarefa)
        }
    }

    fun findById(tarefaId: Int) = lista01.find { it.id == tarefaId }
    fun deleteTarefa(tarefad: Tarefa) {
        lista01.remove(tarefad)
    }


}