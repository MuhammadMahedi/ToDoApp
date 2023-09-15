package com.example.todoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun getTaskDao():TaskDao

    companion object{

        private var db:TaskDatabase?=null

        fun getInstance(context: Context):TaskDatabase{
            if(db==null){
                db= Room.databaseBuilder(context,
                    TaskDatabase::class.java,
                    "task_table")
                    .allowMainThreadQueries()
                    .build()

                return db as TaskDatabase
            }else{
                return db as TaskDatabase
            }
        }

    }
}