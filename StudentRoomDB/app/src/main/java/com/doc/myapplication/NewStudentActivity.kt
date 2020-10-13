package com.doc.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewStudentActivity : AppCompatActivity() {


    private lateinit var editFirstName: EditText
    private lateinit var editLastName: EditText
    private lateinit var editAddress: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)
        editLastName = findViewById(R.id.edit_student_last_name)
        editFirstName = findViewById(R.id.edit_student_first_name)
        editAddress = findViewById(R.id.edit_student_address)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editFirstName.text)
                || TextUtils.isEmpty(editLastName.text)
                || TextUtils.isEmpty(editAddress.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val first_name = editFirstName.text.toString()
                val last_name = editLastName.text.toString()
                val address = editAddress.text.toString()
                replyIntent.putExtra(EXTRA_FIRST, first_name)
                replyIntent.putExtra(EXTRA_ADDRESS, address)
                replyIntent.putExtra(EXTRA_LAST, last_name)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_FIRST = "com.doc.myapplication.students.FIRST"
        const val EXTRA_LAST = "com.doc.myapplication.students.LAST"
        const val EXTRA_ADDRESS = "com.doc.myapplication.students.ADDRESS"
    }
}