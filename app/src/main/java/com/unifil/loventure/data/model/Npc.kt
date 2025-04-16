package com.unifil.loventure.data.model


data class Npc(
    val id: Int,
    val name: String,
    val age: Int,
    val bio: String,
    val img: String,
    val npc: String,
    val gender: String,
    val dialogues: List<Dialogue>
)