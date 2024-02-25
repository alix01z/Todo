package com.alix01z.todoappkotlinmvvm.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.alix01z.todoappkotlinmvvm.room.entites.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {
    @Insert
    fun insertTask(taskEntity: TaskEntity)

    @Delete
    fun deleteTask(taskEntity: TaskEntity)

    @Update
    fun updateTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM table_tasks")
    fun getAllTasks() : Flow<List<TaskEntity>>
}