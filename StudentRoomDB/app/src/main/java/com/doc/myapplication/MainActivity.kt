package com.doc.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doc.myapplication.model.Student
import com.doc.myapplication.model.StudentViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var studentViewModel: StudentViewModel
    private val newStudentActivityRequestCode = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = StudentListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        studentViewModel.allStudents.observe(this, Observer { students ->
            // Update the cached copy of the words in the adapter.
            students?.let { adapter.setStudent(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewStudentActivity::class.java)
            startActivityForResult(intent, newStudentActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newStudentActivityRequestCode && resultCode == Activity.RESULT_OK) {

            var first_name:String = data?.getStringExtra(NewStudentActivity.EXTRA_FIRST).toString()
            var last_name:String = data?.getStringExtra(NewStudentActivity.EXTRA_LAST).toString()
            var address:String = data?.getStringExtra(NewStudentActivity.EXTRA_ADDRESS).toString()

            val student = Student(id=0,first_name,last_name,address)
            studentViewModel.insert(student)

        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
}