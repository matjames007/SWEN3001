package com.doc.socialplatform.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.doc.socialplatform.R

class PostViewModel(application: Application): AndroidViewModel(application) {
    var posts = arrayListOf<Post>()

    init {
        posts.add(Post("Catch a falling Star (Negril)", R.drawable.pic1, "Investor Meet and Greet\n2013 saw the launching of a new FinTech company"))
        posts.add(Post("Portland Party 2013!", R.drawable.pic2, "Birthday Party 2013\nLots of friends and liquor = fun all night into the morning!"))
        posts.add(Post("Nyamings Negril Part 1", R.drawable.pic3, "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. "))
        posts.add(Post("Hard Rock Cafe 2018", R.drawable.pic4, "Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus"))
        posts.add(Post("Silver Sands 2014", R.drawable.pic5, "Beautiful Sunsets"))
        posts.add(Post("Silver Sands 2014 Part 2", R.drawable.pic6, "Friends and Family 2014"))
        posts.add(Post("Silver Sands 2014 Part 3", R.drawable.pic7, "Sunset Chasing"))
        posts.add(Post("Holiday in Ibiza", R.drawable.pic8, "Spanish Party Vibes with friends"))
        posts.add(Post("Holiday in Cancun", R.drawable.pic9, "Vacation 2016"))
    }
}