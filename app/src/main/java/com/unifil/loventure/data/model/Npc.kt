package com.unifil.loventure.data.model


data class Npc(
    val id: Integer,
    val name: String,
    val age: Integer,
    val bio: String,
    val img: String,
    val dialogues:List<Dialogue>
)