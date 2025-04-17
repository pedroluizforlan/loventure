package com.unifil.loventure.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import com.unifil.loventure.R
import com.unifil.loventure.data.model.Npc
import com.unifil.loventure.util.loadAssetImage


@Composable
fun NpcCard(npc: Npc, onClick: () -> Unit) {
    val image = loadAssetImage(npc.img)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column {
            if (image != null) {
                Image(
                    bitmap = image,
                    contentDescription = npc.name,
                    modifier = Modifier
                        .fillMaxWidth(),

                    contentScale = ContentScale.Fit
                )
            } else {

                Image(
                    painter = painterResource(id = R.drawable.npc_placeholder),
                    contentDescription = "Imagem padrão",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(npc.name, style = MaterialTheme.typography.titleLarge, color = Color.Black, fontWeight = FontWeight.Bold)
                Text("${npc.age} anos", style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(10.dp))
                Text(npc.bio, style = MaterialTheme.typography.bodyLarge, maxLines = 2)
            }
        }
    }
}

