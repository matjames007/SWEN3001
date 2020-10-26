package com.doc.fragmentcommunications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.doc.fragmentcommunications.fragments.FragmentA
import com.doc.fragmentcommunications.fragments.FragmentB

class MainActivity : AppCompatActivity(), FragmentCommunicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fragmentA = FragmentA()
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragmentA).commit()
    }

    companion object {
        val MESSAGE_KEY: String = "MESSAGE"
    }

    override fun passFragmentData(message: String) {
        var bundle = Bundle()
        bundle.putString(MESSAGE_KEY, message)

        val fragmentB = FragmentB()
        fragmentB.arguments = bundle

        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragmentB)
        transaction.commit()
    }
}