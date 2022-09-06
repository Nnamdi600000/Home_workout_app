package com.codennamdi.homeworkoutapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CompletedExerciseEntity::class], version = 1)
abstract class CompletedExerciseDatabase : RoomDatabase() {
    abstract fun completedExerciseDao(): CompletedExerciseDao

    companion object {
        @Volatile
        private var INSTANCE: CompletedExerciseDatabase? = null

        fun getInstance(context: Context): CompletedExerciseDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        CompletedExerciseDatabase::class.java,
                        "completed_exercise_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}