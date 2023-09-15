package com.example.todoapp.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Task(

    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo("Title")
    val title:String
):Parcelable
