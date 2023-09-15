package com.example.todoapp.di

import android.content.Context
import com.example.todoapp.data.TaskCmpDao
import com.example.todoapp.data.TaskCmpDatabase
import com.example.todoapp.data.TaskDao
import com.example.todoapp.data.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbProvider {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): TaskDatabase {

        return TaskDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideDao(db:TaskDatabase): TaskDao {

        return db.getTaskDao()
    }

    @Provides
    @Singleton
    fun provideCmpDb(@ApplicationContext context: Context): TaskCmpDatabase {

        return TaskCmpDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideCmpDao(db:TaskCmpDatabase): TaskCmpDao {

        return db.getTaskCmpDao()
    }
}