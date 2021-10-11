package ru.gressor.websockettest.api

import okhttp3.*
import okio.ByteString
import ru.gressor.websockettest.domain.entities.Message
import java.util.concurrent.TimeUnit

class WebSocketTest : WebSocketListener() {

    private fun run() {
        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(0, TimeUnit.MILLISECONDS)
            .build()

        val request: Request = Request.Builder()
            .url(GAME_HOST)
            .build()

        client.newWebSocket(request, this)

        // Trigger shutdown of the dispatcher's executor so this process can exit cleanly.
        client.dispatcher.executorService.shutdown()
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        webSocket.send("""{"data": {"id": "asdkjhfsdkf", "is_actic": true, "sfsdfsdf": "sdfsdfsdf"}}""")
//        webSocket.send("...World!")
        webSocket.close(1000, "Goodbye, World!")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        println("MESSAGE: $text")
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        println("MESSAGE: " + bytes.hex())
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        webSocket.close(1000, null)
        println("CLOSE: $code $reason")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        t.printStackTrace()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val message = Message.ServiceMessage("fff")
            println(message::class.java.canonicalName)
        }
    }
}