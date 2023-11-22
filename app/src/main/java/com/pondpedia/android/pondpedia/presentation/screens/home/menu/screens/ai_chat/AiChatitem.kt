package com.pondpedia.android.pondpedia.presentation.screens.home.menu.screens.ai_chat

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pondpedia.android.pondpedia.domain.model.ai_chat.ChatResponse
import com.pondpedia.android.pondpedia.presentation.ui.theme.PondPediaCustomTheme


@Composable
fun ChatInputItem(
    chatResponse: ChatResponse,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.wrapContentSize(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            contentColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)
        ) {
            Text(text = chatResponse.input)
        }
    }
}

@Composable
fun ChatResponseItem(
    chatResponse: ChatResponse,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.wrapContentSize(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondaryContainer,
            contentColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.PersonPin, contentDescription = "AI Avatar")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = chatResponse.response)
        }
    }
}

@Preview
@Composable
fun ChatResponseItemPreview() {
    PondPediaCustomTheme {
        ChatResponseItem(chatResponse = ChatResponse(
            chatId =  1,
            username = "User",
            input = "Hello!",
            response = "Hello to you too! I hope you have a great day!"
        )
        )
    }
}
@Preview
@Composable
fun ChatInputItemPreview() {
    PondPediaCustomTheme {
        ChatInputItem(
            chatResponse = ChatResponse(
                chatId =  1,
                username = "User",
                input = "Hello!",
                response = "Hello to you too! I hope you have a great day!"
            )
        )
    }
}

@Preview
@Composable
fun AiChatScreenPreview() {
    PondPediaCustomTheme {
        AiChatScreen()
    }
}
