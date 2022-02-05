package com.example.liza

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity(){
    val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_score)


        val spinner = findViewById<SpinnerView>(R.id.spinner)

        val start = findViewById<Button>(R.id.start)

        val scoreOutput = findViewById<TextView>(R.id.score)

        val task = Runnable { scoreOutput.text = "Score: ${spinner.values[5]}" }

        start.setOnClickListener {
            handler.removeCallbacks(task)
            val animation = AnimationUtils.loadAnimation(this, R.anim.clockwise)
            spinner.startAnimation(animation)
            handler.postDelayed(task, 3000)
        }

    }
}