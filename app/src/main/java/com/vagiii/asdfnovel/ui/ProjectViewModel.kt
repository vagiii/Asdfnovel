package com.vagiii.asdfnovel.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vagiii.asdfnovel.data.Project
import com.vagiii.asdfnovel.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {
    val projects = repo.observeProjects()
        .map { it } // placeholder for possible transforms
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addSampleProject(title: String) {
        viewModelScope.launch {
            repo.addProject(title)
        }
    }

    fun delete(project: Project) {
        viewModelScope.launch {
            repo.deleteProject(project)
        }
    }
}
