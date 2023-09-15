package com.example.todoapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskCmpDao {

    @Insert
    fun addTaskCmp(task: Task)

    @Delete
    fun deleteTaskCmp(task: Task)

    @Query("SELECT * FROM Task")
    fun getAllTasksCmp():List<Task>


}