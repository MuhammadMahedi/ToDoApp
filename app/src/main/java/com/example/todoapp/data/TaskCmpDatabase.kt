package com.example.todoapp.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 2)
abstract class TaskCmpDatabase : RoomDatabase() {
    abstract fun getTaskCmpDao():TaskCmpDao

    companion object{

        private var db:TaskCmpDatabase?=null

        fun getInstance(context: Context):TaskCmpDatabase{
            if(db==null){
                db= Room.databaseBuilder(context,
                    TaskCmpDatabase::class.java,
                    "Completed_task_table")
                    .allowMainThreadQueries()
                    .build()

                return db as TaskCmpDatabase
            }else{
                return db as TaskCmpDatabase
            }
        }

    }
}