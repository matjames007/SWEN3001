package com.doc.socialplatform.model.post

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy

@Dao
interface PostDao {

    @Query("SELECT * from posts")
    fun getAllPosts(): LiveData<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post:Post)

    @Query("SELECT * from posts where id=:postId")
    fun getAllPosts(postId: Int): LiveData<List<Post>>

    @Query("DELETE FROM posts")
    suspend fun deleteAll()
}