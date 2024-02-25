package com.alix01z.todoappkotlinmvvm.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alix01z.todoappkotlinmvvm.room.entites.TaskEntity

@Database(entities = [TaskEntity::class] , version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun roomDao() : RoomDao
}