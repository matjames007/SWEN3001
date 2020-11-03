package com.doc.socialplatform

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.doc.socialplatform.model.CustomSensorListener
import com.doc.socialplatform.model.Post
import com.doc.socialplatform.model.PostViewModel
import org.w3c.dom.Text

class PostViewPage : AppCompatActivity() {

    private var position: Int = 0
    private var posts: List<Post> = listOf<Post>()
    private lateinit var sensorListener: CustomSensorListener
    private var tempSensor: Sensor? = null
    private lateinit var sensorManager: SensorManager
    private lateinit var tempView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_view_page)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        posts = ViewModelProvider(this).get(PostViewModel::class.java).posts
        setupScreen()
        setupSensors()
    }

    override fun onResume() {
        super.onResume()
        if(tempSensor != null) {
            sensorManager.registerListener(sensorListener, tempSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        if(tempSensor != null) {
            sensorManager.unregisterListener(sensorListener)
        }
    }

    private fun setupSensors() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
        Log.i(this.toString(), deviceSensors.toString())

        this.tempSensor = if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        } else {
            // Sorry, there are no temperature sensors on your device.
            null
        }

        if(tempSensor != null) {
            sensorListener = CustomSensorListener(tempView, posts[position].temp)
            sensorManager.registerListener(sensorListener, tempSensor, SensorManager.SENSOR_DELAY_NORMAL)
        } else {
            Toast.makeText(this, "No Temperature Sensors Detected!", Toast.LENGTH_SHORT)
        }
    }

    private fun setupScreen() {
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val image = findViewById<ImageView>(R.id.mainPostImage)
        val description = findViewById<TextView>(R.id.description)
        tempView = findViewById(R.id.temp_textview)

        var bundle: Bundle? = intent.extras
        var index = bundle?.getInt(RecyclerListAdapter.POST_ID)
        if(index != null) {
            position = index
        }

        titleTextView.text = posts.get(position).title
        image.setImageResource(posts.get(position).image)
        description.text = posts.get(position).description
        tempView.text = "Temperature ${posts.get(0).temp} degrees Celsius"
    }
}