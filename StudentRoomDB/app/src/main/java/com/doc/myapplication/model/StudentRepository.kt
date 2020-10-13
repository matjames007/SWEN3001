package com.doc.myapplication.model

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class StudentRepository(private val studentDao: StudentDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allStudents: LiveData<List<Student>> = studentDao.getAllLiveStudents()

    suspend fun insert(student: Student) {
        studentDao.insert(student)
    }
}