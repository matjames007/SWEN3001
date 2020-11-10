package com.doc.socialplatform.model

import android.app.Application
import androidx.lifecycle.*
import com.doc.socialplatform.R
import com.doc.socialplatform.model.comment.Comment
import com.doc.socialplatform.model.post.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(application: Application): AndroidViewModel(application) {
    private val repository: CustomRepository
    val allPosts: LiveData<List<Post>>
    val allComments: LiveData<List<Comment>>
    private val postInput = MutableLiveData<Int>()




    init {
        val db = SocialMediaDB.getDatabase(application, viewModelScope)
        val postDao = db.postDao()
        val commentDao = db.commentDao()

        repository = CustomRepository(postDao, commentDao)
        allPosts = repository.posts
        allComments =  Transformations.switchMap(postInput) {
                postID -> repository.getComments(postID)
        }
    }

    fun insert(post: Post) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(post)
    }

    fun insert(comment: Comment) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(comment)
    }

    fun setupComments(postID: Int) {
        postInput.value = postID
    }
}