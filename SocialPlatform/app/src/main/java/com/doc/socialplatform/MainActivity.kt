package com.doc.socialplatform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doc.socialplatform.model.PostViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        val adapter = RecyclerListAdapter(this, postViewModel)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        postViewModel.allPosts.observe(this, Observer {
            posts -> posts?.let { adapter.setPosts(posts) }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when(id) {
            R.id.share_button -> {
                val intent = Intent(this, ProfilePage::class.java)
                startActivity(intent)
            }
            else -> Log.e(this.toString(), "Unknown Error Occurred!")
        }
        return super.onOptionsItemSelected(item)
    }
}