package com.doc.myapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student (
    @PrimaryKey(autoGenerate = true) var id: Int,
    var first_name:String,
    var last_name: String,
    var address: String)