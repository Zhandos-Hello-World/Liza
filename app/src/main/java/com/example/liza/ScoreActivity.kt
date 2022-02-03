package com.example.liza

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_score)

        findViewById<TextView>(R.id.number).text = "${(Math.random() * 300 + 0).toInt()}"

    }
}