package com.unifil.loventure.data.model;

import java.util.List;

data class Dialogue(
        val id: Integer,
        val text: String,
        val options:List<Option>
)