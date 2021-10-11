package ru.gressor.websockettest.api.websocket

import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import ru.gressor.websockettest.api.TEST_USER_ID
import ru.gressor.websockettest.domain.entities.Message

class GameWebSocketListener(
    private val roomId: String,
) : WebSocketListener() {
    val socketEventChannel: Channel<SocketEvent> = Channel(10)
    private val gson = Gson()
    private var webSocket: WebSocket? = null

    override fun onOpen(webSocket: WebSocket, response: Response) {
        println(roomId)
        this.webSocket = webSocket
        webSocket.send(
            gson.toJson(
                OpenRequest(roomId, TEST_USER_ID)
            )
        )
        webSocket.send(
            gson.toJson(
                Message.UserMessage("test_user", "test text")
            )
        )
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        println("onMessage: $text")
        GlobalScope.launch {
            socketEventChannel.send(
                SocketEvent.TextData(text)
            )
        }
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        println("onClosing: $code / $reason")
        GlobalScope.launch {
            socketEventChannel.send(
                SocketEvent.Error(SocketAbortedException())
            )
        }
        webSocket.close(WebServicesProvider.NORMAL_CLOSURE_STATUS, null)
        socketEventChannel.close()
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        println("onFailure")
        GlobalScope.launch {
            socketEventChannel.send(
                SocketEvent.Error(t)
            )
        }
    }
}