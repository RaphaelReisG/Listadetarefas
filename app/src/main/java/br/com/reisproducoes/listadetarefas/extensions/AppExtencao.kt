package br.com.reisproducoes.listadetarefas.extensions

import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

private val localle01 = Locale("pt", "BR")

fun Date.format() : String {
    return SimpleDateFormat("dd/MM/yyyy", localle01).format(this)
}

var TextInputLayout.texto01 : String
    get() = editText?.text?.toString() ?: ""
    set(value){
        editText?.setText(value)
    }