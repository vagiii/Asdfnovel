package com.vagiii.asdfnovel.data

import androidx.room.Database
import androidx.room.RoomDatabase

// DB version bumped to 2 to include documents table
@Database(entities = [Project::class, Document::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
    abstract fun documentDao(): DocumentDao
}
