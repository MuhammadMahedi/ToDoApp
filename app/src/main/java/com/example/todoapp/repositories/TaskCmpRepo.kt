package com.example.todoapp.repositories

import com.example.todoapp.data.Task
import com.example.todoapp.data.TaskCmp
import com.example.todoapp.data.TaskCmpDao
import javax.inject.Inject


class TaskCmpRepo @Inject constructor(private val dao:TaskCmpDao) {

    fun getAllTasks():List<Task>{
        return dao.getAllTasksCmp()
    }

    fun deleteTask(task: Task){
        dao.deleteTaskCmp(task)
    }

    fun addTask(task: Task){
        dao.addTaskCmp(task)
    }
}