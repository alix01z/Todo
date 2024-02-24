package com.alix01z.todoappkotlinmvvm.viewModel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.alix01z.todoappkotlinmvvm.R
import com.alix01z.todoappkotlinmvvm.repository.AppRepository
import com.alix01z.todoappkotlinmvvm.room.entites.TaskEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel
@Inject constructor(private val repository: AppRepository) : ViewModel() {
    //Documents suggests using MutableLivedata and convert it to LiveData
    private var dbData : MutableLiveData<List<TaskEntity>> = MutableLiveData()
    var dbLiveData : LiveData<List<TaskEntity>> = dbData


    //Room
    fun insertTaskToDB(taskEntity: TaskEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertTask(taskEntity)
        }
    }

    fun deleteTaskFromDB(taskEntity: TaskEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteTask(taskEntity)
        }
    }

    fun updateTask(taskEntity: TaskEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateTask(taskEntity)
        }
    }

    fun getAllTasksFromDB(){
        viewModelScope.launch(Dispatchers.IO){
            val allTasks = repository.getAllTasks().observe()

        }
    }

    //OnClickListeners
    fun fabNewTaskOnClick(view: View){
        view.findNavController().navigate(R.id.action_homeFragment_to_newTodoFragment)
    }
}