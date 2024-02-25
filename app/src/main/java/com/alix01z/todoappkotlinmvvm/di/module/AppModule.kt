package com.alix01z.todoappkotlinmvvm.di.module

import android.app.Application
import androidx.room.Room
import com.alix01z.todoappkotlinmvvm.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomDB(application: Application):AppDatabase =
        Room.databaseBuilder(application , AppDatabase::class.java , "TaskDB")
            .fallbackToDestructiveMigration()
            .build()
}