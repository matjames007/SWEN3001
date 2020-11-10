package com.doc.socialplatform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.doc.socialplatform.model.post.Post
import com.doc.socialplatform.model.PostViewModel
import com.doc.socialplatform.model.comment.Comment

class PostViewPage : AppCompatActivity() {

    private var position: Int = 0
    private var posts: List<Post> = listOf<Post>()
    private lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_view_page)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        postViewModel.allPosts.observe(this, Observer {
            posts -> this.posts = posts
            setupScreen()
        })
    }

    private fun setupScreen() {
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val image = findViewById<ImageView>(R.id.mainPostImage)
        val description = findViewById<TextView>(R.id.description)
        val commentButton = findViewById<ImageButton>(R.id.commentButton)
        val commentTextView = findViewById<EditText>(R.id.commentBox)
        commentButton.setOnClickListener {
            val commentObject = Comment(0,posts[position].id, commentTextView.text.toString())
            postViewModel.insert(commentObject)
            Toast.makeText(this, "Comment Successfully Added!", Toast.LENGTH_SHORT).show()
        }

        var bundle: Bundle? = intent.extras
        var index = bundle?.getInt(RecyclerListAdapter.POST_ID)
        if(index != null) {
            position = index
        }

        titleTextView.text = posts[position].title
        image.setImageResource(posts[position].image)
        description.text = posts[position].description
    }
}