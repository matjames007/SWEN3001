package com.doc.myapplication.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StudentDao {

    @Query("SELECT * FROM student ORDER BY last_name ASC")
    fun getAllStudents(): List<Student>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(student: Student)

    @Query("DELETE FROM student")
    suspend fun deleteAll()

    @Query("SELECT * from student ORDER BY last_name ASC")
    fun getAllLiveStudents(): LiveData<List<Student>>
}