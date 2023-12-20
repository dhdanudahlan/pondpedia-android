package com.pondpedia.android.pondpedia.presentation.ui.home.menu.screens.ai_chat

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pondpedia.android.pondpedia.domain.model.ai_chat.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AiChatViewModel @Inject constructor(
//    pager: Pager<Int, ChatResponseEntity>
): ViewModel() {

    private val _messageText = mutableStateOf("")
    val messageText: State<String> = _messageText

    private val _state = mutableStateOf(ChatState())
    val state: State<ChatState> = _state

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent.asSharedFlow()


    fun onMessageChange(message: String) {
        _messageText.value = message
    }

    fun connectToChat() {
        getAllMessages()
//        savedStateHandle.get<String>("username")?.let { username ->
//            viewModelScope.launch {
//                val result = chatSocketService.initSession(username)
//                when(result) {
//                    is Resource.Success -> {
//                        chatSocketService.observeMessages()
//                            .onEach { message ->
//                                val newList = state.value.messages.toMutableList().apply {
//                                    add(0, message)
//                                }
//                                _state.value = state.value.copy(
//                                    messages = newList
//                                )
//                            }.launchIn(viewModelScope)
//                    }
//                    is Resource.Error -> {
//                        _toastEvent.emit(result.message ?: "Unknown error")
//                    }
//                }
//            }
//        }
    }
    fun disconnect() {
//        viewModelScope.launch {
//            chatSocketService.closeSession()
//        }
    }
    fun getAllMessages() {
//        viewModelScope.launch {
//            _state.value = state.value.copy(isLoading = true)
//            val result = messageService.getAllMessages()
//            _state.value = state.value.copy(
//                messages = result,
//                isLoading = false
//            )
//        }
        val newList = listOf(
            Message(
                text = "Berapa range pH yang baik untuk budidaya ikan nila?",
                formattedTime = "15/12/2023 11:44",
                username = "User"
            ),
            Message(
                text = "Budidaya ikan nila umumnya dilakukan pada lingkungan air dengan pH yang berkisar antara 6 hingga 8,5. Rentang pH ini dianggap optimal untuk pertumbuhan dan perkembangan ikan nila. Namun perlu diperhatikan bahwa Nilai pH sekitar 7 hingga 7,5 dianggap ideal untuk budidaya ikan nila. Ini menciptakan kondisi lingkungan yang netral hingga sedikit basa.",
                formattedTime = "15/12/2023 11:44",
                username = "ChatGPT"
            )
        ).reversed()
        _state.value = state.value.copy(
            messages = newList
        )
    }

    fun sendMessage() {
        viewModelScope.launch {
//            if(messageText.value.isNotBlank()) {
//                chatSocketService.sendMessage(messageText.value)
//            }
        }
    }

    override fun onCleared() {
//        super.onCleared()
//        disconnect()
    }
}