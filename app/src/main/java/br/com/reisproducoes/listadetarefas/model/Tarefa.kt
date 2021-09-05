package br.com.reisproducoes.listadetarefas.model

data class Tarefa(
    val titulo: String,
    val hora: String,
    val data: String,
    val id: Int = 0
){
    override fun equals(other: Any?): Boolean{
        if(this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tarefa

        if(id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }

}
