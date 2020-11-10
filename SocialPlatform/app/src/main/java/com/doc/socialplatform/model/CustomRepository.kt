package com.doc.socialplatform.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.doc.socialplatform.model.comment.Comment
import com.doc.socialplatform.model.comment.CommentDao
import com.doc.socialplatform.model.post.Post
import com.doc.socialplatform.model.post.PostDao

class CustomRepository(private val postDao: PostDao, private val commentDao: CommentDao) {

    val posts: LiveData<List<Post>> = postDao.getAllPosts()
    val comment: LiveData<List<Comment>> = MutableLiveData<List<Comment>>()

    suspend fun insert(post: Post) {
        postDao.insertPost(post)
    }

    suspend fun insert(comment: Comment) {
        commentDao.insertComment(comment)
    }

    fun getComments(postId: Int): LiveData<List<Comment>> {
        return commentDao.getAllCommentsForPost(postId)
    }
}