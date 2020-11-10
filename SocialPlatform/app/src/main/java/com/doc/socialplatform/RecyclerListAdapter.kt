package com.doc.socialplatform

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.doc.socialplatform.model.post.Post
import com.doc.socialplatform.model.PostViewModel

class RecyclerListAdapter internal constructor(var context: Context, viewModel: PostViewModel): RecyclerView.Adapter<RecyclerListAdapter.PostViewHolder>(){
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var posts = listOf<Post>()

    companion object {
        val POST_ID = "POST_ID"
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val description: TextView = itemView.findViewById(R.id.description)
        val share_button: ImageButton = itemView.findViewById(R.id.imageButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = inflater.inflate(R.layout.row_record, parent, false)
        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val current = posts[position]
        holder.titleTextView.text = current.title
        holder.imageView.setImageResource(current.image)
        holder.imageView.setOnClickListener {
            var intent = Intent(context, PostViewPage::class.java).apply {
                putExtra(RecyclerListAdapter.POST_ID, position)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.applicationContext.startActivity(intent)
        }
        holder.description.text = current.description
        holder.share_button.setOnClickListener {
            val shareIntent = Intent().apply {
                this.action = Intent.ACTION_SEND
                this.putExtra(Intent.EXTRA_TEXT, current.description)
                this.putExtra(Intent.EXTRA_TITLE, current.title)

                var resources: Resources = context.resources
                var uri = Uri.Builder()
                    .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                    .authority(resources.getResourcePackageName(current.image))
                    .appendPath(resources.getResourceTypeName(current.image))
                    .appendPath(resources.getResourceEntryName(current.image))
                type = "image/jpeg"
                this.putExtra(Intent.EXTRA_STREAM, uri.build())
            }
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.applicationContext.startActivity(shareIntent)
        }
    }

    internal fun setPosts(posts: List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    override fun getItemCount() = posts.size
}