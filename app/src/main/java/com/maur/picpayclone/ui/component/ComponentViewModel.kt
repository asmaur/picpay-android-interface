package com.maur.picpayclone.ui.component

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ComponentViewModel: ViewModel() {

    private val _componentes = MutableLiveData<Componentes>().also {
        it.value = temComponente
    }

    val componentes: LiveData<Componentes> = _componentes

    var temComponente = Componentes()

    set(value){
        field = value
        _componentes.value = value
    }
}

data class Componentes(
    val bottonNavigation: Boolean = false
)