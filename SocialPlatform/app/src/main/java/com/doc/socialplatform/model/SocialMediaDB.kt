package com.doc.socialplatform.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.doc.socialplatform.model.comment.Comment
import com.doc.socialplatform.model.comment.CommentDao
import com.doc.socialplatform.model.post.Post
import com.doc.socialplatform.model.post.PostDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Post::class, Comment::class], version = 2, exportSchema = false)
public abstract class SocialMediaDB: RoomDatabase() {

    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: SocialMediaDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): SocialMediaDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SocialMediaDB::class.java,
                    "student_database"
                ).addCallback(SocialAppDBCallback(scope)).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class SocialAppDBCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.postDao())
                }
            }
        }

        suspend fun populateDatabase(postDao: PostDao) {
        }
    }
}