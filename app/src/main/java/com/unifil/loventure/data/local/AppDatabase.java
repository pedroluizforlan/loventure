package com.unifil.loventure.data.local;

import androidx.room.Database;

import com.unifil.loventure.data.local.entity.ConversationState;
import com.unifil.loventure.data.local.entity.User;

@Database(entities = [User::class, ConversationState::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun conversationStateDao(): ConversationStateDao
}
