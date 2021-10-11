package ru.gressor.websockettest.api.websocket

import kotlinx.coroutines.channels.Channel
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.logging.HttpLoggingInterceptor
import ru.gressor.websockettest.api.GAME_HOST
import ru.gressor.websockettest.api.TEST_USER_ID
import java.util.concurrent.TimeUnit

class WebServicesProvider {
    private var webSocket: WebSocket? = null

    private val loggingInterceptor = HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }

    private val socketOkHttpClient = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .addInterceptor(loggingInterceptor)
        .hostnameVerifier { _, _ -> true }
        .build()

    private var webSocketListener: GameWebSocketListener? = null

    fun startSocket(roomId: String): Channel<SocketEvent> =
        with(GameWebSocketListener((roomId))) {
            startSocket(this)
            this@with.socketEventChannel
        }

    private fun startSocket(webSocketListener: GameWebSocketListener) {
        this.webSocketListener = webSocketListener
        webSocket = socketOkHttpClient.newWebSocket(
            Request.Builder()
                .url(GAME_HOST)
                .addHeader("perfect_user_id", TEST_USER_ID)
                .build(),
            webSocketListener
        )

        socketOkHttpClient.dispatcher.executorService.shutdown()
    }

    fun stopSocket() {
        try {
            webSocket?.close(NORMAL_CLOSURE_STATUS, null)
            webSocket = null
            webSocketListener?.socketEventChannel?.close()
            webSocketListener = null
        } catch (t: Throwable) {
        }
    }

    companion object {
        const val NORMAL_CLOSURE_STATUS = 1000
    }
}