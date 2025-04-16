package com.unifil.loventure.ui.navigation

object NavRoutes {
    const val LOGIN = "login"
    const val NPC_LIST = "npc_list"
    const val CHAT = "chat/{npcId}"

    fun chatWith(npcId: Int) = "chat/$npcId"
}