package ru.gressor.websockettest.api.retrofit

import com.google.gson.annotations.SerializedName

data class HandshakeResponse(
    @SerializedName("prepare_room_id")
    val prepareRoomId: String,
    @SerializedName("rank")
    val rank: Int = 0,
    @SerializedName("themes")
    val themes: List<String> = listOf(),
    @SerializedName("time_left")
    val timeLeft: Int = 3600,
)