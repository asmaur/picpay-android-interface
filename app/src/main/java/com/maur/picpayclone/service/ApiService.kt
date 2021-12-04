package com.maur.picpayclone.service

import com.maur.picpayclone.data.Usuario
import com.maur.picpayclone.data.transacao.Transacao
import com.maur.picpayclone.data.transacao.TransacaoPage
import retrofit2.http.*

interface ApiService {
    @GET("/usuarios/contatos/")
    suspend fun getContatos(@Query("login") login: String): List<Usuario>

    @GET("/usuario/{login}/saldo")
    suspend fun getSaldo(@Path("login") login: String): Usuario

    @POST("/transacoes")
    suspend fun transferir(@Body transacao: Transacao): Transacao

    @GET("/transacoes")
    suspend fun getTransacao(@Query("login") login: String): TransacaoPage
}