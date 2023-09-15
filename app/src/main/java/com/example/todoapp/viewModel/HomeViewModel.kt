package com.example.todoapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.Task
import com.example.todoapp.repositories.TaskRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var repo: TaskRepo):ViewModel() {
    private var _taskList:MutableLiveData<List<Task>> = MutableLiveData<List<Task>>()
    val taskList:LiveData<List<Task>> get() = _taskList

    fun getAllTasks(){
        _taskList.postValue(repo.getAllTasks())
    }

    fun addTask(task:Task){
        repo.addTask(task)
        getAllTasks()
    }

    fun deleteTask(task:Task){
        repo.deleteTask(task)
        getAllTasks()
    }
}