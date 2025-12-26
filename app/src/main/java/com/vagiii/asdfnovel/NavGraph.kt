package com.vagiii.asdfnovel

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavHostController
import com.vagiii.asdfnovel.ui.ProjectListScreen
import com.vagiii.asdfnovel.ui.ProjectDetailScreen
import com.vagiii.asdfnovel.ui.EditorScreen

object Routes {
    const val PROJECT_LIST = "project_list"
    const val PROJECT_DETAIL = "project_detail/{projectId}"
    const val PROJECT_DETAIL_BASE = "project_detail"
    const val EDITOR = "editor/{projectId}/{docId}"
    const val EDITOR_BASE = "editor"
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.PROJECT_LIST) {
        composable(Routes.PROJECT_LIST) {
            ProjectListScreen(onOpenProject = { projectId ->
                navController.navigate("${Routes.PROJECT_DETAIL_BASE}/$projectId")
            })
        }

        composable(
            route = Routes.PROJECT_DETAIL,
            arguments = listOf(navArgument("projectId") { type = NavType.StringType })
        ) { backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId") ?: return@composable
            ProjectDetailScreen(projectId = projectId, onOpenEditor = { docId ->
                navController.navigate("${Routes.EDITOR_BASE}/$projectId/$docId")
            }, onBack = { navController.popBackStack() })
        }

        composable(
            route = Routes.EDITOR,
            arguments = listOf(
                navArgument("projectId") { type = NavType.StringType },
                navArgument("docId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId") ?: return@composable
            val docId = backStackEntry.arguments?.getString("docId") ?: ""
            EditorScreen(projectId = projectId, docId = docId, onBack = { navController.popBackStack() })
        }
    }
}
