package ru.gressor.websockettest.api.websocket

import com.google.gson.annotations.SerializedName
import ru.gressor.websockettest.api.TEST_USER_ID

data class OpenRequest(
    @SerializedName("prepare_room_id")
    val prepareRoomId: String,
    @SerializedName("user_id")
    val userId: String = TEST_USER_ID,
)