package ru.gressor.websockettest.api.websocket

import okio.ByteString

sealed interface SocketEvent {
    data class TextData(val text: String) : SocketEvent
    data class BytesData(val bytes: ByteString) : SocketEvent
    data class Error(val throwable: Throwable) : SocketEvent
}