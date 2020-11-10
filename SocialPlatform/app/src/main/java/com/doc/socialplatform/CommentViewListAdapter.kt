package com.doc.socialplatform

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.doc.socialplatform.model.comment.Comment
import com.doc.socialplatform.model.PostViewModel

class CommentViewListAdapter internal constructor(var context: Context, viewModel: PostViewModel): RecyclerView.Adapter<CommentViewListAdapter.CommentViewHolder>(){
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var comments = listOf<Comment>()


    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.profilePic)
        val commentTextView: TextView = itemView.findViewById(R.id.commentText)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val current = comments[position]
        holder.commentTextView.text = current.comment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val itemView = inflater.inflate(R.layout.comment_row_item, parent, false)
        return CommentViewHolder(itemView)
    }

    fun setComments(comments: List<Comment>) {
        this.comments = comments
        notifyDataSetChanged()
    }
}
