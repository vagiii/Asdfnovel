package com.vagiii.asdfnovel.di

import android.content.Context
import androidx.room.Room
import com.vagiii.asdfnovel.data.AppDatabase
import com.vagiii.asdfnovel.data.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "asdfnovel-db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideRepository(db: AppDatabase): Repository = Repository(db)
}
