package com.unifil.loventure.data.model;

import java.util.List;

data class Npc(
        val id: Integer,
        val name: String,
        val age: Integer,
        val bio: String,
        val img: String,
        val dialogues:List<Dialogue>
)
