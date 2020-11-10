package com.doc.socialplatform.model.post

import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
class Post (@PrimaryKey(autoGenerate = true) var id: Int,
            var title: String,
            var image: Int,
            var description: String)