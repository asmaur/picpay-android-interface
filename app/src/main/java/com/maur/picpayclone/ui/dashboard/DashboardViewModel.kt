package com.maur.picpayclone.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maur.picpayclone.data.Usuario
import com.maur.picpayclone.data.UsuarioLogado
import com.maur.picpayclone.service.ApiService
import kotlinx.coroutines.launch

class DashboardViewModel(private val apiService: ApiService) : ViewModel() {

    private val _contatos = MutableLiveData<List<Usuario>>()
    val contatos: LiveData<List<Usuario>> = _contatos


    init {

        viewModelScope.launch{
            try {
                val login = UsuarioLogado.usuario.login
                _contatos.value = apiService.getContatos(login)
            } catch (e: Exception){
                Log.e("Erro", e.message ?: "")
            }
        }

    }

}