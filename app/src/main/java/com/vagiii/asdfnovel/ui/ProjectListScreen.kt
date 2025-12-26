package com.vagiii.asdfnovel.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vagiii.asdfnovel.data.Project

@Composable
fun ProjectListScreen(viewModel: ProjectViewModel = hiltViewModel()) {
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
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text("No projects yet — tap + to create one")
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                items(list, key = { it.id }) { project ->
                    ProjectRow(project = project, onDelete = { viewModel.delete(it) })
                }
            }
        }
    }
}

@Composable
fun ProjectRow(project: Project, onDelete: (Project) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), elevation = 4.dp) {
        Row(modifier = Modifier.padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
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
