package com.doc.sensorapplication

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.widget.TextView

class CustomSensorListener(val textView: TextView, val unit: String) : SensorEventListener {

    private var capture:Boolean = false

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        capture = when(accuracy) {
            SensorManager.SENSOR_STATUS_ACCURACY_HIGH -> true
            SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM -> true
            else -> false
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val value = event?.values?.get(0)

        if(capture) {
            if (value != null) {
                textView.text = "$value $unit"
            }
        }
    }
}