package com.unifil.loventure

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unifil.loventure.data.model.Npc
import com.unifil.loventure.ui.theme.LoventureTheme
import com.unifil.loventure.util.JsonUtils

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Agora a lista virá do wrapper como você já fez
        val npcList = JsonUtils.loadNpcList(this)

        setContent {
            LoventureTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NpcListScreen(
                        npcList = npcList,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun NpcListScreen(npcList: List<Npc>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Text("NPCs Disponíveis", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        npcList.forEach { npc ->
            Text("🧑 ${npc.name} (${npc.age} anos)", style = MaterialTheme.typography.bodyLarge)
            Text(npc.bio, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}