package com.doc.socialplatform.model.comment

import com.doc.socialplatform.model.post.Post
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy

@Dao
interface CommentDao {

    @Query("SELECT * from comments")
    fun getAllComments(): LiveData<List<Comment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: Comment)

    @Query("SELECT * from comments where postId=:postId")
    fun getAllCommentsForPost(postId: Int): LiveData<List<Comment>>

    @Query("DELETE FROM comments")
    suspend fun deleteAll()

}


