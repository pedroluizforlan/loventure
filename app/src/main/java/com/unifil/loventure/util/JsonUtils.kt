package com.unifil.loventure.util

import android.content.Context
import com.google.gson.Gson
import com.unifil.loventure.data.model.Npc
import android.util.Log

data class NpcListWrapper(val npc: List<Npc>)

object JsonUtils {
    fun loadNpcList(context: Context): List<Npc> {
        return try {
            val jsonString = context.assets.open("npcs.json")
                .bufferedReader()
                .use { it.readText() }

            val wrapper = Gson().fromJson(jsonString, NpcListWrapper::class.java)
            wrapper.npc
        } catch (e: Exception) {
            Log.e("JsonUtils", "Erro ao carregar NPCs: ${e.message}")
            emptyList() // Retorna lista vazia para evitar crash
        }
    }
}