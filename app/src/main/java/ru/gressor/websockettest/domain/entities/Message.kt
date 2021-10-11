package ru.gressor.websockettest.domain.entities

sealed interface Message {
    data class ServiceMessage(
        val text: String
    ): Message

    data class UserMessage(
        val user: String,
        val text: String
    ): Message
}