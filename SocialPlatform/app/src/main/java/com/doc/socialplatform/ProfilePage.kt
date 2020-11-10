package com.doc.socialplatform

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.app.NavUtils
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.doc.socialplatform.model.PostViewModel
import kotlinx.android.synthetic.main.social_page_profile.*
import org.w3c.dom.Text

class ProfilePage : AppCompatActivity() {
    lateinit var postViewModel: PostViewModel
    lateinit var picture: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.social_page_profile)

        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        buildProfile()

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }

    companion object {
        val IMAGE_CAPTURE: Int = 33
    }

    private fun buildProfile():Unit {
        var picturePostCount:TextView = findViewById(R.id.picture_posts)
        var email: TextView = findViewById(R.id.email)
        var friendsCount:TextView = findViewById(R.id.friends)
        var contact:TextView = findViewById(R.id.contact)
        var share_button:Button = findViewById(R.id.share_intent)
        picture = findViewById(R.id.profile_pic)

        postViewModel.allPosts.observe(this, Observer{
            posts -> picturePostCount.text = "${posts.size} Posts!"
        })

        friendsCount.text = "0 friends!"
        email.text = "matthew.budram02@uwimona.edu.jm"
        contact.text = "(876) XXX-YYYY"

        picture.setOnClickListener {
            println("Testing")
            val cameraIntent = Intent().apply {
                action = MediaStore.ACTION_IMAGE_CAPTURE
            }
            try {
                startActivityForResult(cameraIntent, ProfilePage.IMAGE_CAPTURE)
            } catch (e: ActivityNotFoundException) {
                Log.e(this.toString(), e.toString())
                Toast.makeText(this, "Unable to launch camera", Toast.LENGTH_SHORT).show()
            }
        }




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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK && requestCode == ProfilePage.IMAGE_CAPTURE) {
            val image = data?.extras?.get("data") as Bitmap
            profile_pic.setImageBitmap(image)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
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