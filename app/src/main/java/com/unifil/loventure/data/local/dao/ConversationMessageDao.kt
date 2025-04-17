package com.unifil.loventure.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.unifil.loventure.data.local.entity.ConversationMessage

@Dao
interface ConversationMessageDao {
    @Insert
    suspend fun insert(message: ConversationMessage)

    @Query("SELECT * FROM conversation_messages WHERE userId = :userId AND npcId = :npcId ORDER BY timestamp ASC")
    suspend fun getMessagesForConversation(userId: Int, npcId: Int): List<ConversationMessage>

    @Query("DELETE FROM conversation_messages WHERE userId = :userId AND npcId = :npcId")
    suspend fun clearConversation(userId: Int, npcId: Int)
}