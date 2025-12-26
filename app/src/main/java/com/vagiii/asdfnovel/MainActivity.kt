package com.vagiii.asdfnovel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import com.vagiii.asdfnovel.ui.theme.AsdfNovelTheme
import com.vagiii.asdfnovel.NavGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AsdfNovelTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
