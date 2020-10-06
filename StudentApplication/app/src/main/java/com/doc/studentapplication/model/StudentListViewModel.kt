package com.doc.studentapplication.model

import androidx.lifecycle.ViewModel

class StudentListViewModel : ViewModel() {
    val students = mutableListOf<Student>()

    init {
        students += Student("Matthew", "Budram", true, "2 Worthington Avenue\nKingston 5")
        students += Student("Nathan", "Colman", true, "1 John Doe Lane\nKingston 5")
        students += Student("Mark", "Johnson", true, "10 Johnson Close\nKingston 5")
        students += Student("Harold", "Willis", false, "33 Markcam Street\nKingston 15")
        students += Student("Kumar", "Santoki", false, "43 Birdman Way\nKingston 1")
        students += Student("Matthew", "Budram", true, "2 Worthington Avenue\nKingston 5")
        students += Student("Nathan", "Colman", true, "1 John Doe Lane\nKingston 5")
        students += Student("Mark", "Johnson", true, "10 Johnson Close\nKingston 5")
        students += Student("Harold", "Willis", false, "33 Markcam Street\nKingston 15")
        students += Student("Kumar", "Santoki", false, "43 Birdman Way\nKingston 1")
        students += Student("Matthew", "Budram", true, "2 Worthington Avenue\nKingston 5")
        students += Student("Nathan", "Colman", true, "1 John Doe Lane\nKingston 5")
        students += Student("Mark", "Johnson", true, "10 Johnson Close\nKingston 5")
        students += Student("Harold", "Willis", false, "33 Markcam Street\nKingston 15")
        students += Student("Kumar", "Santoki", false, "43 Birdman Way\nKingston 1")
        students += Student("Matthew", "Budram", true, "2 Worthington Avenue\nKingston 5")
        students += Student("Nathan", "Colman", true, "1 John Doe Lane\nKingston 5")
        students += Student("Mark", "Johnson", true, "10 Johnson Close\nKingston 5")
        students += Student("Harold", "Willis", false, "33 Markcam Street\nKingston 15")
        students += Student("Kumar", "Santoki", false, "43 Birdman Way\nKingston 1")
        students += Student("Matthew", "Budram", true, "2 Worthington Avenue\nKingston 5")
        students += Student("Nathan", "Colman", true, "1 John Doe Lane\nKingston 5")
        students += Student("Mark", "Johnson", true, "10 Johnson Close\nKingston 5")
        students += Student("Harold", "Willis", false, "33 Markcam Street\nKingston 15")
        students += Student("Kumar", "Santoki", false, "43 Birdman Way\nKingston 1")

    }
}