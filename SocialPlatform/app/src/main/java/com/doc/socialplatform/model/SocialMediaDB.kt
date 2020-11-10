package com.doc.socialplatform.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.doc.socialplatform.R
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
                    populateDatabase(database.postDao(), database.commentDao())
                }
            }
        }

        suspend fun populateDatabase(postDao: PostDao, commentDao: CommentDao) {
            postDao.deleteAll()
            commentDao.deleteAll()

            val posts = arrayListOf<Post>()
            posts.add(Post(0,"Catch a falling Star (Negril)", R.drawable.pic1, "Investor Meet and Greet\n2013 saw the launching of a new FinTech company"))
            posts.add(Post(0,"Portland Party 2013!", R.drawable.pic2, "Birthday Party 2013\nLots of friends and liquor = fun all night into the morning!"))
            posts.add(Post(0,"Nyamings Negril Part 1", R.drawable.pic3, "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. "))
            posts.add(Post(0,"Hard Rock Cafe 2018", R.drawable.pic4, "Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus" ))
            posts.add(Post(0,"Silver Sands 2014", R.drawable.pic5, "Beautiful Sunsets"))
            posts.add(Post(0,"Silver Sands 2014 Part 2", R.drawable.pic6, "Friends and Family 2014"))
            posts.add(Post(0,"Silver Sands 2014 Part 3", R.drawable.pic7, "Sunset Chasing"))
            posts.add(Post(0,"Holiday in Ibiza", R.drawable.pic8, "Spanish Party Vibes with friends"))
            posts.add(Post(0,"Holiday in Cancun", R.drawable.pic9, "Vacation 2016"))

            for(post in posts) {
                postDao.insertPost(post)
            }
        }
    }
}