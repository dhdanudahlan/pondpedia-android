package com.pondpedia.compose.pondpedia.presentation.screens.home.menu.screens.ai_chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.pondpedia.compose.pondpedia.core.util.DateGenerator
import com.pondpedia.compose.pondpedia.domain.model.ai_chat.ChatResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiChatScreen(
    chats: List<ChatResponse> = listOf(
        ChatResponse(
            chatId =  1,
            username = "User",
            input = "Hai!",
            response = "Hai juga!"
        ),
        ChatResponse(
            chatId =  1,
            username = "User",
            input = "Tanggal Berapakah ini?",
            response = "Hari ini adalah tanggal ${DateGenerator.getCurrentDate()}!"
        )
    )
) {
    /*val context = LocalContext.current
    LaunchedEffect(key1 = chats.loadState) {
        if (chats.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (chats.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }*/

    var message by rememberSaveable {
        mutableStateOf("")
    }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Icon(imageVector = Icons.Default.PersonPin, contentDescription = "Avatar")
                        Text(text = "GPT-4 Turbo")
                    }
                },
                navigationIcon = {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Return Icon")
                },
                actions = {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Action Icon")
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    chats.forEach {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.TopStart
                        ) {
                            ChatResponseItem(chatResponse = it)
                        }
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            ChatInputItem(chatResponse = it)
                        }
                    }
                }
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = message,
                        onValueChange = { message = it }
                    )
                    IconButton(
                        modifier = Modifier.fillMaxHeight(),
                        onClick = { message = "" }
                    ) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = "Send Message")
                    }
                }
            }
        }
    }
}