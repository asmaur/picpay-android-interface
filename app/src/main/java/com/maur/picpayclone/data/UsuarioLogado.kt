package com.maur.picpayclone.data

object UsuarioLogado {
    lateinit var usuario: Usuario

    fun isUsuarioLogado() = this::usuario.isInitialized

    fun isUsuarioNaoLogado() = !isUsuarioLogado()
}