package com.maur.picpayclone.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maur.picpayclone.data.Usuario
import com.maur.picpayclone.data.UsuarioLogado
import com.maur.picpayclone.data.transacao.Transacao
import com.maur.picpayclone.service.ApiService
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(private val apiService: ApiService) : ViewModel() {

    private val _saldo = MutableLiveData<Double>()
    val saldo: LiveData<Double> = _saldo
    val onError = MutableLiveData<String>()

    private val _transacoes = MutableLiveData<List<Transacao>>()
    val transacoes: LiveData<List<Transacao>> = _transacoes

    val onLoading = MutableLiveData<Boolean>()

    init {
        onLoading.value = true
        viewModelScope.launch {
            try {
                onLoading.value = true
                val login = UsuarioLogado.usuario.login
                _saldo.value = apiService.getSaldo(login).saldo
                _transacoes.value = apiService.getTransacao(login).content

            }catch (e: Exception){
                //Log.i("Erro", "Erro no saldo")
                onError.value = e.message
            }
        }
        onLoading.value = false
    }

}