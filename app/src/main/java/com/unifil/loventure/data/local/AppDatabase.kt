package com.unifil.loventure.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.unifil.loventure.data.local.dao.ConversationMessageDao
import com.unifil.loventure.data.local.dao.ConversationStateDao
import com.unifil.loventure.data.local.dao.UserDao
import com.unifil.loventure.data.local.entity.ConversationMessage
import com.unifil.loventure.data.local.entity.ConversationState
import com.unifil.loventure.data.local.entity.User

@Database(entities = [User::class, ConversationState::class, ConversationMessage::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun conversationStateDao(): ConversationStateDao
    abstract fun conversationMessageDao(): ConversationMessageDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "loventure-db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}