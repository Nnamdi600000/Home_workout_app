package com.codennamdi.homeworkoutapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Completed Exercise History")
data class CompletedExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "time and date")
    val timeAndDate: String
    )
