package com.unifil.loventure.data.repository

import com.unifil.loventure.data.local.dao.ConversationMessageDao
import com.unifil.loventure.data.local.entity.ConversationMessage

class ConversationMessageRepository(private val dao: ConversationMessageDao) {
    suspend fun insertMessage(message: ConversationMessage) = dao.insert(message)

    suspend fun getConversation(userId: Int, npcId: Int): List<ConversationMessage> =
        dao.getMessagesForConversation(userId, npcId)

    suspend fun clearConversation(userId: Int, npcId: Int) =
        dao.clearConversation(userId, npcId)
}