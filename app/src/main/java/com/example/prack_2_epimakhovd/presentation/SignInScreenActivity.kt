package com.example.prack_2_epimakhovd.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.prack_2_epimakhovd.R
import com.google.gson.Gson

class SignInScreenActivity : ComponentActivity() {
    private lateinit var loginButton: Button
    private lateinit var email: EditText
    private lateinit var password: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_screen)

        email = findViewById(R.id.edit_email)
        password = findViewById(R.id.edit_password)
        loginButton = findViewById(R.id.button_enter)

        val prefs = getSharedPreferences("user", MODE_PRIVATE)
        val gson = Gson()

        loginButton.setOnClickListener {
            val email = email.text.toString().trim()
            val password = password.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Введите email и пароль", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Проверяем, есть ли сохранённые данные
            val savedJson = prefs.getString("user_json", null)

            if (savedJson == null) {
                // Первый вход — сохраняем
                val user = mapOf("email" to email, "password" to password)
                val json = gson.toJson(user)
                prefs.edit().putString("user_json", json).apply()
                Toast.makeText(this, "Аккаунт создан! Вход выполнен.", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainScreenActivity::class.java))
                finish()
            } else {
                // Последующие входы — проверяем
                val savedUser = gson.fromJson(savedJson, Map::class.java)
                val savedEmail = savedUser["email"] as? String
                val savedPassword = savedUser["password"] as? String

                if (email == savedEmail && password == savedPassword) {
                    Toast.makeText(this, "Вход выполнен!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainScreenActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Неверный email или пароль", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}