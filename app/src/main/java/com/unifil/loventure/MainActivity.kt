package com.unifil.loventure

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.unifil.loventure.data.local.AppDatabase
import com.unifil.loventure.data.model.Npc
import com.unifil.loventure.ui.components.NpcCard
import com.unifil.loventure.ui.navigation.AppNavigation
import com.unifil.loventure.ui.theme.LoventureTheme


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // ⚙️ Instanciando o banco e um userId fictício
        val database = AppDatabase.getInstance(applicationContext)
        val userId = 1 // depois você pode pegar isso do login

        setContent {
            LoventureTheme {
                val navController = rememberNavController()
                var userId by rememberSaveable { mutableStateOf<Int?>(null) }
                val database = AppDatabase.getInstance(applicationContext)
                
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    AppNavigation(
                        navController = navController,
                        userId = userId,
                        database = database,
                        onLoginSuccess = { loggedUserId ->
                            userId = loggedUserId
                        }
                    )
                }
            }
        }
    }
}
