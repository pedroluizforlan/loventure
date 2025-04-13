package com.unifil.loventure.util

import android.content.Context
import com.google.gson.Gson
import com.unifil.loventure.data.model.Npc


data class NpcListWrapper(val npc: List<Npc>)

object JsonUtils {
    fun loadNpcList(context: Context): List<Npc> {
        val jsonString = context.assets.open("npcs.json").bufferedReader().use { it.readText() }
        val wrapper = Gson().fromJson(jsonString, NpcListWrapper::class.java)
        return wrapper.npc
    }
}