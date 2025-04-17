package com.unifil.loventure.ui.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.unifil.loventure.data.model.Npc
import com.unifil.loventure.ui.components.NpcCard
import com.unifil.loventure.util.JsonUtils
import com.unifil.loventure.ui.navigation.NavRoutes

@Composable
fun NpcListScreen(
    npcList: List<Npc>,
    onNpcClick: (Npc) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.fillMaxSize().background(Color(0xFFE91E63)).padding(16.dp) ) {
        Spacer(modifier = Modifier.height(30.dp))

        Text("Usuários Online", fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(npcList) { npc ->
                NpcCard(npc = npc, onClick = { onNpcClick(npc) })
            }
        }
    }
}