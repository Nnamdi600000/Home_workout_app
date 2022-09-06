package com.codennamdi.homeworkoutapp

import android.app.Application

class CompletedExerciseApp: Application() {
    val db by lazy {
        CompletedExerciseDatabase.getInstance(this)
    }
}