package com.unifil.loventure.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.unifil.loventure.data.local.entity.ConversationState;

@Dao
interface ConversationStateDao {
    @Insert
    suspend fun insert(conversationState:ConversationState)

    @Query("SELECT * FROM conversation_state_table WHERE userId = :userId AND npcId = :npcId")
    suspend fun getConversationState(userId: Int, npcId: Int): ConversationState
}