package com.alix01z.todoappkotlinmvvm.repository

import androidx.lifecycle.LiveData
import com.alix01z.todoappkotlinmvvm.room.AppDatabase
import com.alix01z.todoappkotlinmvvm.room.entites.TaskEntity
import javax.inject.Inject

class AppRepository
@Inject
constructor(appDatabase: AppDatabase) {

    private val roomDao = appDatabase.roomDao()

    fun insertTask(taskEntity: TaskEntity){
        roomDao.insertTask(taskEntity)
    }

    fun deleteTask(taskEntity: TaskEntity){
        roomDao.deleteTask(taskEntity)
    }

    fun updateTask(taskEntity: TaskEntity){
        roomDao.updateTask(taskEntity)
    }

    fun getAllTasks():LiveData<List<TaskEntity>>{
        return roomDao.getAllTasks()
    }
}