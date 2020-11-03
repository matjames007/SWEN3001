package com.doc.socialplatform.model

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import android.widget.TextView

class CustomSensorListener(val textView: TextView, val postTemp: Int): SensorEventListener {

    private var capture:Boolean = false

    override fun onSensorChanged(event: SensorEvent?) {
        val temp = event?.values?.get(0)
        if (capture) {
            if (temp != null) {
                textView.text = "Temperature Diff: ${temp - postTemp} degrees Celsius"
            }
        }
    }

    /**
     * I am only interested in medium to high accuracy readings!
     * I am setting the capture flag to true only if this criteria is met
     */
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        capture = when(accuracy) {
            SensorManager.SENSOR_STATUS_ACCURACY_HIGH -> {
                true
            }
            SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM -> {
                true
            }
            else -> {
                Log.v(this.toString(),"Accuracy too low")
                false
            }
        }
    }
}