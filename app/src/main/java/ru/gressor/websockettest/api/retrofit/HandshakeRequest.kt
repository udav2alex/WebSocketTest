package ru.gressor.websockettest.api.retrofit

data class HandshakeRequest(
    val id: String,
    val rank : Int = 0,
    val themes : List<String> = listOf()
)