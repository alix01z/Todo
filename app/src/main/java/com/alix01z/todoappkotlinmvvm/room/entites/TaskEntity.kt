package com.alix01z.todoappkotlinmvvm.room.entites

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("task_id")
    val id: Int,

    @ColumnInfo("task_title")
    var title: String?,

    @ColumnInfo("task_comment")
    var comment: String?,

    @ColumnInfo("task_priority")
    val priority: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(comment)
        parcel.writeString(priority)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TaskEntity> {
        override fun createFromParcel(parcel: Parcel): TaskEntity {
            return TaskEntity(parcel)
        }

        override fun newArray(size: Int): Array<TaskEntity?> {
            return arrayOfNulls(size)
        }
    }
}
