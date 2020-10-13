package com.doc.myapplication.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Student::class), version = 1, exportSchema = false)
public abstract class StudentRoomDatabase : RoomDatabase() {

   abstract fun studentDao(): StudentDao

   companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: StudentRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): StudentRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudentRoomDatabase::class.java,
                        "student_database"
                    ).addCallback(StudentDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }
   }

    private class StudentDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.studentDao())
                }
            }
        }

        suspend fun populateDatabase(studentDao: StudentDao) {
            // Delete all content here.
            studentDao.deleteAll()

            // Add sample students.
            var student = Student(0, "Matthew","Budram", "2 Worthington Avenue\nKingston 5")
            studentDao.insert(student)
            student = Student(0, "Laura","Budram", "2 Worthington Avenue\nKingston 5")
            studentDao.insert(student)

        }
    }
}
