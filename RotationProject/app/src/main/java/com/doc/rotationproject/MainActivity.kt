package com.doc.rotationproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    var characterName: String = ""
    lateinit var characterLabel: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var text = findViewById<EditText>(R.id.editTextTextPersonName)
        var save = findViewById<Button>(R.id.button)
        characterLabel = findViewById<TextView>(R.id.textView)
        save.setOnClickListener {
            setAppCharacterName(text.text.toString())
        }
    }

    private fun setAppCharacterName(name:String) {
        characterName = name
        characterLabel.text = characterName
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val character = characterName
        outState.putString("savedCharacter", character)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val name = savedInstanceState.getString("savedCharacter").toString()
        setAppCharacterName(name)
    }
}