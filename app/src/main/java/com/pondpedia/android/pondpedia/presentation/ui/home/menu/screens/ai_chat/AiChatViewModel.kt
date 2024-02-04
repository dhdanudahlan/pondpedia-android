package com.pondpedia.android.pondpedia.presentation.ui.home.menu.screens.ai_chat

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pondpedia.android.pondpedia.core.util.Resource
import com.pondpedia.android.pondpedia.data.remote.dto.ai_chat.ChatRequest
import com.pondpedia.android.pondpedia.domain.model.ai_chat.Chat
import com.pondpedia.android.pondpedia.domain.use_case.ai_chat.AddChatUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ai_chat.GetChatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AiChatViewModel @Inject constructor(
    private val addChatUseCase: AddChatUseCase,
    private val getChatUseCase: GetChatUseCase
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

    fun onErrorDialogDismissed() {
        _state.value = state.value.copy(
            isError = false,
            errorMessage = ""
        )
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
    private fun getAllMessages() {
        viewModelScope.launch {
           getChatUseCase().collect {
                _state.value = state.value.copy(
                    chats = it.reversed()
                )
           }
        }
    }

    fun sendMessage() {
        viewModelScope.launch {
//            if(messageText.value.isNotBlank()) {
//                chatSocketService.sendMessage(messageText.value)
//            }

            val message = _messageText.value
            _messageText.value = ""
            if(message.isNotBlank()) {
                val request = ChatRequest(message)
                when(val result = addChatUseCase(request)) {
                    is Resource.Error -> _state.value = state.value.copy(
                        isError = true,
                        errorMessage = result.message ?: "Terjadi kesalahan, silahkan coba lagi."
                    )
                    else -> {}
                }
            }
        }
    }

    override fun onCleared() {
//        super.onCleared()
//        disconnect()
    }
}