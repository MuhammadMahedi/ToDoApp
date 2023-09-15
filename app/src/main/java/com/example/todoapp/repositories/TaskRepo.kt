package com.example.todoapp.repositories

import com.example.todoapp.data.Task
import com.example.todoapp.data.TaskDao
import javax.inject.Inject

class TaskRepo @Inject constructor(private val dao: TaskDao){

    fun getAllTasks():List<Task>{
        return dao.getAllTasks()
    }

    fun deleteTask(task: Task){
        dao.deleteTask(task)
    }

    fun addTask(task:Task){
        dao.addTask(task)
    }


}