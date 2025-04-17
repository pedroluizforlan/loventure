package com.unifil.loventure.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.unifil.loventure.data.local.AppDatabase
import com.unifil.loventure.data.local.entity.ConversationMessage
import com.unifil.loventure.data.model.Npc
import com.unifil.loventure.data.model.Dialogue
import com.unifil.loventure.data.model.Option
import com.unifil.loventure.ui.navigation.NavRoutes
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(
    navController: NavHostController,
    npc: Npc,
    userId: Int?,
    database: AppDatabase
) {
    val messageDao = database.conversationMessageDao()
    val coroutineScope = rememberCoroutineScope()
    var chatHistory by remember { mutableStateOf<List<Pair<String, Boolean>>>(emptyList()) }
    var currentDialogueId by remember { mutableStateOf(1) }

    LaunchedEffect(Unit) {
        val savedMessages = messageDao.getMessagesForConversation(userId!!, npc.id)
        chatHistory = savedMessages.map { it.message to it.isUser }
    }

    val currentDialogue = npc.dialogues.find { it.id == currentDialogueId }

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFE91E63)).padding(16.dp)) {
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.navigate(NavRoutes.NPC_LIST) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.Black
                )
            }
            Text(
                text = "${npc.name} 💬",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(chatHistory) { (message, isUser) ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
                ) {
                    Text(
                        text = message,
                        color = if (isUser) Color.White else Color.Black,
                        modifier = Modifier
                            .background(if (isUser) Color(0xFFF48FB1) else Color(0xFFECECEC))
                            .padding(12.dp)
                    )
                }
            }
        }

        Divider()

        currentDialogue?.let { dialogue ->
            LaunchedEffect(currentDialogueId) {
                if (chatHistory.none { it.first == dialogue.text }) {
                    chatHistory = chatHistory + (dialogue.text to false)
                    coroutineScope.launch {
                        messageDao.insert(
                            ConversationMessage(userId = userId!!, npcId = npc.id, message = dialogue.text, isUser = false)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column {
                dialogue.options.forEach { option ->
                    Button(
                        onClick = {
                            chatHistory = chatHistory + (option.text to true)
                            currentDialogueId = option.next

                            coroutineScope.launch {
                                messageDao.insert(
                                    ConversationMessage(userId = userId!!, npcId = npc.id, message = option.text, isUser = true)
                                )
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFCE4EC)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                    ) {
                        Text(option.text, color = Color.Black)
                    }
                }
            }
        } ?: run {
            Text("Fim da conversa.")
        }
    }
}