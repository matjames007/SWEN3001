package com.doc.socialplatform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.doc.socialplatform.model.Post
import com.doc.socialplatform.model.PostViewModel
import org.w3c.dom.Text

class PostViewPage : AppCompatActivity() {

    private var position: Int = 0
    private var posts: List<Post> = listOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_view_page)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        posts = ViewModelProvider(this).get(PostViewModel::class.java).posts
        setupScreen()
    }

    private fun setupScreen() {
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val image = findViewById<ImageView>(R.id.mainPostImage)
        val description = findViewById<TextView>(R.id.description)

        var bundle: Bundle? = intent.extras
        var index = bundle?.getInt(RecyclerListAdapter.POST_ID)
        if(index != null) {
            position = index
        }

        titleTextView.text = posts.get(position).title
        image.setImageResource(posts.get(position).image)
        description.text = posts.get(position).description
    }
}