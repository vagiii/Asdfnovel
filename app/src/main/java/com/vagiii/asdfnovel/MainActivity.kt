package com.vagiii.asdfnovel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import com.vagiii.asdfnovel.ui.theme.AsdfNovelTheme
import com.vagiii.asdfnovel.ui.ProjectListScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AsdfNovelTheme {
                ProjectListScreen()
            }
        }
    }
}
