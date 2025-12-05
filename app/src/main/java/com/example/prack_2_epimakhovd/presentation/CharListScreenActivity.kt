package com.example.prack_2_epimakhovd.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.prack_2_epimakhovd.R

class CharListScreenActivity : ComponentActivity() {
    private lateinit var backText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list_screen)

        backText = findViewById(R.id.back_edit)
        backText.setOnClickListener {
            startActivity(Intent(this, MainScreenActivity::class.java))
            finish()
        }
    }


}