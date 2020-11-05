package com.doc.sensorapplication

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var sensorManager: SensorManager
    private val sensorMap :HashMap<Int,Sensor?> = HashMap<Int,Sensor?>()
    private val sensorListenerMap: HashMap<Int, CustomSensorListener> = HashMap<Int, CustomSensorListener>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupSensors()
    }

    private fun setupSensors() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        sensorMap[Sensor.TYPE_AMBIENT_TEMPERATURE] = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        sensorMap[Sensor.TYPE_LIGHT] = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        sensorMap[Sensor.TYPE_RELATIVE_HUMIDITY] = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
        sensorMap[Sensor.TYPE_PRESSURE] = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
        sensorMap[Sensor.TYPE_PROXIMITY] = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        sensorListenerMap[Sensor.TYPE_AMBIENT_TEMPERATURE] = CustomSensorListener(findViewById(R.id.temp), "Celsius")
        sensorListenerMap[Sensor.TYPE_LIGHT] = CustomSensorListener(findViewById(R.id.light), "Lux")
        sensorListenerMap[Sensor.TYPE_RELATIVE_HUMIDITY] = CustomSensorListener(findViewById(R.id.humidity), "%")
        sensorListenerMap[Sensor.TYPE_PRESSURE] = CustomSensorListener(findViewById(R.id.pressure), "hPa")
        sensorListenerMap[Sensor.TYPE_PROXIMITY] = CustomSensorListener(findViewById(R.id.proximity), "cm")

        registerListeners()
    }

    override fun onPause() {
        super.onPause()
        unregisterListeners()
    }

    override fun onResume() {
        super.onResume()
        registerListeners()
    }

    private fun registerListeners() {
        for ((sensorType, listener) in sensorListenerMap) {
            if(sensorMap[sensorType] != null) {
                sensorManager.registerListener(listener, sensorMap[sensorType], SensorManager.SENSOR_DELAY_NORMAL)
            }
        }
    }

    private fun unregisterListeners() {
        for ((sensorType, listener) in sensorListenerMap) {
            if(sensorMap[sensorType] != null) {
                sensorManager.unregisterListener(listener)
            }
        }
    }
}