package com.maur.picpayclone.data

data class CartaoCredito(
    val bandeira: BandeiraCartao = BandeiraCartao.VISA,
    val numeroCartao: String = "",
    val nomeTitular: String = "",
    val dataExpiracao: String = "",
    val codigoSeguranca: String = "",
    val numeroToken: String = "",
    val usuario: Usuario = Usuario(),
    val isSalvar: Boolean = false

)
