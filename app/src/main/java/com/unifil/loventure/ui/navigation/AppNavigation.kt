package com.unifil.loventure.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.unifil.loventure.data.local.AppDatabase
import com.unifil.loventure.ui.screen.LoginScreen
import com.unifil.loventure.ui.screen.NpcListScreen
import com.unifil.loventure.ui.screen.ChatScreen
import com.unifil.loventure.util.JsonUtils


@Composable
fun AppNavigation(
    navController: NavHostController,
    userId: Int?,
    database: AppDatabase,
    onLoginSuccess: (Int) -> Unit
) {
    val context = LocalContext.current
    val npcList = JsonUtils.loadNpcList(context)
    NavHost(navController = navController, startDestination = NavRoutes.LOGIN) {
        composable(NavRoutes.LOGIN) {
            LoginScreen(
                navController,
                onLoginSuccess = onLoginSuccess
            )

        }
        composable(NavRoutes.NPC_LIST) {

            NpcListScreen(
                npcList = npcList,
                onNpcClick = { selectedNpc ->
                    navController.navigate("chat/${selectedNpc.id}")
                }
            )
        }
        composable(
            route = NavRoutes.CHAT,
            arguments = listOf(navArgument("npcId") { type = NavType.IntType })
        ) { backStackEntry ->
            val npcId = backStackEntry.arguments?.getInt("npcId")
            val npc = npcList.find { it.id == npcId }

            if (npc != null && npcId != null) {
                ChatScreen(
                    navController = navController,
                    npc = npc,
                    userId = userId,
                    database = database
                )
            }
        }
    }
}