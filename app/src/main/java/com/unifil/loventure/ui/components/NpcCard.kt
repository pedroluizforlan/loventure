package com.unifil.loventure.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.unifil.loventure.R
import com.unifil.loventure.data.model.Npc

@Composable
fun NpcCard(npc: Npc, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = R.drawable.npc_placeholder),
                contentDescription = npc.name,
                modifier = Modifier
                    .size(64.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(npc.name, style = MaterialTheme.typography.titleMedium)
                Text("${npc.age} anos", style = MaterialTheme.typography.bodySmall)
                Text(npc.bio, style = MaterialTheme.typography.bodyMedium, maxLines = 2)
            }
        }
    }
}