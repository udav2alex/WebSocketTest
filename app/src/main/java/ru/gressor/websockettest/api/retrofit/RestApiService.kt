package ru.gressor.websockettest.api.retrofit

import retrofit2.http.Body
import retrofit2.http.POST

interface RestApiService {
    @POST("game/start/friends/")
    suspend fun handshake(@Body request: HandshakeRequest): HandshakeResponse
}