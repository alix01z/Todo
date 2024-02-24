package com.alix01z.todoappkotlinmvvm.room.entites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("task_id")
    val id: Int ,

    @ColumnInfo("task_title")
    val title: String ,

    @ColumnInfo("task_comment")
    val comment: String ,

    @ColumnInfo("task_priority")
    val priority: String
)
