package com.doc.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.doc.myapplication.model.Student

class StudentListAdapter internal constructor(context: Context): RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(){
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var students = emptyList<Student>() // Cached copy of Students

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentNameView: TextView = itemView.findViewById(R.id.name)
        val studentAddress: TextView = itemView.findViewById(R.id.address)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val current = students[position]
        holder.studentNameView.text = "${current.first_name} ${current.last_name}"
        holder.studentAddress.text = current.address
    }

    internal fun setStudent(students: List<Student>) {
        this.students = students
        notifyDataSetChanged()
    }

    override fun getItemCount() = students.size
}