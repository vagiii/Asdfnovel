package com.vagiii.asdfnovel.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import com.vagiii.asdfnovel.data.Project

@Composable
fun ProjectListScreen(
    viewModel: ProjectViewModel = hiltViewModel(),
    onOpenProject: (String) -> Unit = {}
) {
    val projects = viewModel.projects

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Asdfnovel — Projects") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.addSampleProject("New Project") }) {
                Text("+")
            }
        }
    ) { padding ->
        val list = projects.value
        if (list.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("No projects yet — tap + to create one")
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                items(list, key = { it.id }) { project ->
                    ProjectRow(project = project, onDelete = { viewModel.delete(it) }, onOpen = { onOpenProject(project.id) })
                }
            }
        }
    }
}

@Composable
fun ProjectRow(project: Project, onDelete: (Project) -> Unit, onOpen: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable { onOpen() }, elevation = 4.dp) {
        Row(modifier = Modifier.padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(project.title, style = MaterialTheme.typography.h6)
                Text("Updated ${android.text.format.DateFormat.getDateFormat(LocalContext.current).format(project.modifiedAt)}", style = MaterialTheme.typography.body2)
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = { onDelete(project) }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}
