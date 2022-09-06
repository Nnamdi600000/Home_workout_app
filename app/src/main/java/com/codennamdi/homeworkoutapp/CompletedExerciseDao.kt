package com.codennamdi.homeworkoutapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CompletedExerciseDao {
    @Insert
    suspend fun insert(completedExerciseEntity: CompletedExerciseEntity)

    @Query("SELECT * FROM `Completed Exercise History`")
    fun loadAllCompletedExercises(): Flow<List<CompletedExerciseEntity>>
}