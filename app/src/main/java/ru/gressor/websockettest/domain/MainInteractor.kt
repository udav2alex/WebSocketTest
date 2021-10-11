package ru.gressor.websockettest.domain

import kotlinx.coroutines.channels.Channel
import ru.gressor.websockettest.api.websocket.SocketEvent
import ru.gressor.websockettest.repository.MainRepository

class MainInteractor(private val repository: MainRepository) {

    suspend fun connect() =
        repository.connect()

//    fun startSocket(): Channel<SocketEvent> =
//        repository.startSocket()
//
//    fun stopSocket() =
//        repository.closeSocket()
}