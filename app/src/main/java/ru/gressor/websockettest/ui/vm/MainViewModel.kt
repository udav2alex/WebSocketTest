package ru.gressor.websockettest.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.gressor.websockettest.api.websocket.SocketEvent
import ru.gressor.websockettest.domain.MainInteractor
import ru.gressor.websockettest.domain.entities.Message
import java.nio.charset.Charset

class MainViewModel(private val interactor: MainInteractor): ViewModel() {

    private val mutableStateFlow : MutableStateFlow<List<Message>> = MutableStateFlow(listOf())

    fun connect() {
        viewModelScope.launch {
            interactor.connect()
        }
    }

//    fun subscribeToSocketEvents() {
//        viewModelScope.launch(Dispatchers.IO) {
//            interactor.startSocket().consumeEach {
//                try {
//                    when (it) {
//                        is SocketEvent.TextData -> println(it)
//                        is SocketEvent.BytesData -> println(it.bytes.string(Charset.defaultCharset()))
//                        is SocketEvent.Error -> println(it.throwable.message)
//                    }
//                } catch (t: Throwable) {
//                    println(t.message)
//                }
//            }
//        }
//    }
//
//    override fun onCleared() {
//        interactor.stopSocket()
//        super.onCleared()
//    }
}