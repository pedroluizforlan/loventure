package com.unifil.loventure.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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
    Column(modifier = modifier.padding(16.dp)) {
        Text("NPCs Disponíveis", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(npcList) { npc ->
                NpcCard(npc = npc, onClick = { onNpcClick(npc) })
            }
        }
    }
}