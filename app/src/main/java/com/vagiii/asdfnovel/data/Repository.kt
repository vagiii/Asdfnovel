package com.vagiii.asdfnovel.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val db: AppDatabase
) {
    private val dao = db.projectDao()

    fun observeProjects(): Flow<List<Project>> = dao.observeAll()

    suspend fun addProject(title: String) {
        val p = Project(title = title)
        dao.insert(p)
    }

    suspend fun deleteProject(project: Project) {
        dao.delete(project)
    }
}
