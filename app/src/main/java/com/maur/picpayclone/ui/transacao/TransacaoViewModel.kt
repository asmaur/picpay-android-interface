package com.maur.picpayclone.ui.transacao

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maur.picpayclone.data.Usuario
import com.maur.picpayclone.data.transacao.Transacao
import com.maur.picpayclone.service.ApiService
import kotlinx.coroutines.launch
import java.lang.Exception

class TransacaoViewModel(private val apiService: ApiService) : ViewModel() {

    private val _transacao = MutableLiveData<Transacao>()
    val transacao: LiveData<Transacao> = _transacao


    fun transferir(transacao: Transacao) {
        viewModelScope.launch {
            try {
                val transacaoFeita = apiService.transferir(transacao)
                _transacao.value = transacaoFeita

            }catch (e: Exception){
                Log.e("Erro", "Erro ao transferir")
            }
        }
    }
    // TODO: Implement the ViewModel
}