package com.example.prack_2_epimakhovd.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.prack_2_epimakhovd.R

class MoviesScreenActivity : ComponentActivity() {

    private lateinit var textBack: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_activity)

        textBack = findViewById(R.id.back_edit)
        textBack.setOnClickListener {
            startActivity(Intent(this, MainScreenActivity::class.java))
            finish()
        }
    }
}
