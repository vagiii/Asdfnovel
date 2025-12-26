package com.vagiii.asdfnovel.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val db: AppDatabase
) {
    private val projectDao = db.projectDao()
    private val documentDao = db.documentDao()

    /* Projects */
    fun observeProjects(): Flow<List<Project>> = projectDao.observeAll()

    suspend fun addProject(title: String) {
        val p = Project(title = title)
        projectDao.insert(p)
    }

    suspend fun deleteProject(project: Project) {
        projectDao.delete(project)
    }

    /* Documents */
    fun observeDocumentsForProject(projectId: String): Flow<List<Document>> =
        documentDao.observeForProject(projectId)

    suspend fun addDocument(document: Document): Long {
        return documentDao.insert(document)
    }

    suspend fun updateDocument(document: Document) {
        documentDao.update(document)
    }

    suspend fun deleteDocument(document: Document) {
        documentDao.delete(document)
    }
}
