package com.unifil.loventure.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.unifil.loventure.ui.screen.LoginScreen
import com.unifil.loventure.ui.screen.NpcListScreen
import com.unifil.loventure.ui.screen.ChatScreen
import com.unifil.loventure.util.JsonUtils



@Composable
fun AppNavigation(navController: NavHostController) {
    val context = LocalContext.current
    val npcList = JsonUtils.loadNpcList(context)
    NavHost(navController = navController, startDestination = NavRoutes.LOGIN) {
        composable(NavRoutes.LOGIN) {
            LoginScreen(navController)
        }
        composable(NavRoutes.NPC_LIST) {

            NpcListScreen(
                npcList = npcList,
                onNpcClick = { selectedNpc ->
                    navController.navigate("chat/${selectedNpc.id}")
                }
            )
        }
        composable(NavRoutes.CHAT) { backStackEntry ->
            val npcId = backStackEntry.arguments?.getString("npcId")?.toIntOrNull()
            val npc = npcList.find { it.id == npcId }

            if (npc != null) {
                ChatScreen(navController = navController, npc = npc)
            }
        }
    }
}