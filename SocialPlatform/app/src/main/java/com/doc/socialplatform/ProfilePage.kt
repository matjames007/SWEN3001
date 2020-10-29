package com.doc.socialplatform

import android.content.ContentResolver
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.app.NavUtils
import androidx.lifecycle.ViewModelProvider
import com.doc.socialplatform.model.PostViewModel
import org.w3c.dom.Text

class ProfilePage : AppCompatActivity() {
    lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.social_page_profile)

        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        buildProfile()

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }

    private fun buildProfile():Unit {
        var picturePostCount:TextView = findViewById(R.id.picture_posts)
        var email: TextView = findViewById(R.id.email)
        var friendsCount:TextView = findViewById(R.id.friends)
        var contact:TextView = findViewById(R.id.contact)
        var share_button:Button = findViewById(R.id.share_intent)

        picturePostCount.text = "${postViewModel.posts.size} Posts!"
        friendsCount.text = "0 friends!"
        email.text = "matthew.budram02@uwimona.edu.jm"
        contact.text = "(876) XXX-YYYY"

        share_button.setOnClickListener {
            val shareIntent = Intent().apply {
                this.action = Intent.ACTION_SEND
                this.putExtra(Intent.EXTRA_TEXT,
                    "Matthew Budram's Contact:\n${email.text}\n${contact.text}")
                type = "text/plain"
            }
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(shareIntent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this);
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}