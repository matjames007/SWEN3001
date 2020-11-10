package com.doc.socialplatform.model.comment


import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
class Comment (@PrimaryKey(autoGenerate = true) var id: Int,
                var postId: Int,
                var comment: String)