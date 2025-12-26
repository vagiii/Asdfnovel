package com.vagiii.asdfnovel.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vagiii.asdfnovel.data.Document
import com.vagiii.asdfnovel.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentsViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {

    fun observeDocuments(projectId: String): Flow<List<Document>> {
        return repo.observeDocumentsForProject(projectId)
    }

    suspend fun createDocumentSync(projectId: String, title: String = "Untitled"): Long {
        val d = Document(projectId = projectId, title = title)
        return repo.addDocument(d)
    }

    fun createDocument(projectId: String, title: String = "Untitled", onResult: (Long) -> Unit) {
        viewModelScope.launch {
            val id = repo.addDocument(Document(projectId = projectId, title = title))
            onResult(id)
        }
    }

    fun deleteDocument(document: Document) {
        viewModelScope.launch {
            repo.deleteDocument(document)
        }
    }

    fun updateDocument(document: Document) {
        viewModelScope.launch {
            repo.updateDocument(document)
        }
    }
}
