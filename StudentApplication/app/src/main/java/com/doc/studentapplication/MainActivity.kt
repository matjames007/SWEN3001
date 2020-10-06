package com.doc.studentapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.doc.studentapplication.model.StudentListViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val student_listView:ListView = findViewById(R.id.student_listView)
        student_listView.adapter = StudentViewAdapter(StudentListViewModel(),this)
    }
}