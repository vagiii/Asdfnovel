package com.vagiii.asdfnovel.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

@Composable
fun ProjectDetailScreen(
    projectId: String,
    onOpenEditor: (String) -> Unit = {},
    onBack: () -> Unit = {}
) {
    val docsVm: DocumentsViewModel = hiltViewModel()
    val docsFlow = docsVm.observeDocuments(projectId)
    val docsState = docsFlow.collectAsState(initial = emptyList())
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Project") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // create doc then navigate to editor
                coroutineScope.launch {
                    docsVm.createDocument(projectId) { id ->
                        onOpenEditor(id.toString())
                    }
                }
            }) {
                Text("New")
            }
        }
    ) { padding ->
        val docs = docsState.value
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(8.dp)) {
            items(docs) { doc ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)) {
                    Row(modifier = Modifier.padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(doc.title)
                        Button(onClick = { onOpenEditor(doc.id.toString()) }) { Text("Open") }
                    }
                }
            }
        }
    }
}
