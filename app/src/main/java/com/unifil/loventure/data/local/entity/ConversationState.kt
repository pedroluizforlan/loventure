package com.unifil.loventure.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversation_state_table")
data class ConversationState(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val npcId: Int,
    val currentDialogueId: Int
)