package com.vagiii.asdfnovel.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProjectDetailScreen(projectId: String, onOpenEditor: (String) -> Unit = {}, onBack: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Project") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = androidx.compose.material.icons.Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onOpenEditor("new-doc") }) {
                Text("Edit")
            }
        }
    ) { padding ->
        // Placeholder list of documents — replace with real data
        val docs = listOf("Chapter 1", "Chapter 2", "Scene A")
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding).padding(8.dp)) {
            items(docs) { d ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Row(modifier = Modifier.padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(d)
                        Button(onClick = { onOpenEditor(d) }) { Text("Open") }
                    }
                }
            }
        }
    }
}
