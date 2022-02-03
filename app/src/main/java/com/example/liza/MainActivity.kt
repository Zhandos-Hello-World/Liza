package com.example.liza

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_start)

        val start = findViewById<Button>(R.id.start)
        val web = findViewById<Button>(R.id.web_btn)
        val webSample = findViewById<Button>(R.id.web_btn_sample)

        start.setOnClickListener {
            val intent = Intent(this, ScoreActivity::class.java)
            startActivity(intent)
        }
        web.setOnClickListener {
            val intent = Intent(this, WebActivity::class.java)
            startActivity(intent)
        }
        webSample.setOnClickListener {
            val intent = Intent(this, WebActivity::class.java)
            intent.putExtra("Link", "https://www.google.com")
            startActivity(intent)
        }
    }
}