package ru.gressor.websockettest.repository

import kotlinx.coroutines.channels.Channel
import ru.gressor.websockettest.api.TEST_USER_ID
import ru.gressor.websockettest.api.retrofit.HandshakeRequest
import ru.gressor.websockettest.api.retrofit.RestApiService
import ru.gressor.websockettest.api.websocket.SocketEvent
import ru.gressor.websockettest.api.websocket.WebServicesProvider

class MainRepository(
    private val restApiService: RestApiService,
    private val webServicesProvider: WebServicesProvider,
) {
    suspend fun connect() {
        val response = restApiService
            .handshake(
                HandshakeRequest(TEST_USER_ID, 2, listOf("theme1", "theme2"))
            )
        println(response)

        val prepareRoomId = response.prepareRoomId
        webServicesProvider.startSocket(prepareRoomId)
    }

//    fun startSocket(): Channel<SocketEvent> =
//        webServicesProvider.startSocket()
//
//    fun closeSocket() {
//        webServicesProvider.stopSocket()
//    }
}