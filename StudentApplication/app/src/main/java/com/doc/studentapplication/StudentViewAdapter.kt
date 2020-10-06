package com.doc.studentapplication

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.doc.studentapplication.model.StudentListViewModel

class StudentViewAdapter(studentListViewModel: StudentListViewModel, c_context: Context) : BaseAdapter() {
    private val studentsModel: StudentListViewModel
    private val context: Context

    init {
        studentsModel = studentListViewModel
        context = c_context
    }
    override fun getCount(): Int {
        return studentsModel.students.count()
    }

    override fun getItem(position: Int): Any {
        return studentsModel.students[position]
    }

    override fun getItemId(position: Int): Long {
        return studentsModel.students[position].hashCode().toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflator = LayoutInflater.from(context)
        val row = layoutInflator.inflate(R.layout.student_list_row, parent, false)
        row.findViewById<TextView>(R.id.student_name).text = studentsModel.students[position].first_name + " " + studentsModel.students[position].last_name
        row.findViewById<TextView>(R.id.student_address).text = studentsModel.students[position].address
        return row
    }

}