package com.example.prack_2_epimakhovd.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.prack_2_epimakhovd.R
import com.google.gson.Gson
import com.google.android.material.snackbar.Snackbar

class SignInScreenActivity : AppCompatActivity() {
    private lateinit var loginButton: Button
    private lateinit var email: EditText
    private lateinit var password: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_screen)

        email = findViewById(R.id.edit_email)
        password = findViewById(R.id.edit_password)
        loginButton = findViewById(R.id.loginButton)

        val rView = findViewById<View>(android.R.id.content)

        val prefs = getSharedPreferences("user", MODE_PRIVATE)
        val gson = Gson()

        loginButton.setOnClickListener {
            val email = email.text.toString().trim()
            val password = password.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Snackbar.make(rView, "Введите email и пароль", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Проверяем, есть ли сохранённые данные
            val savedJson = prefs.getString("user_json", null)

            if (savedJson == null) {
                // Первый вход — сохраняем
                val user = mapOf("email" to email, "password" to password)
                val json = gson.toJson(user)
                prefs.edit().putString("user_json", json).apply()
                Snackbar.make(rView, "Аккаунт создан! Вход выполнен.", Snackbar.LENGTH_SHORT).show()
                startActivity(Intent(this, MainScreenActivity::class.java))
                finish()
            } else {
                // Последующие входы — проверяем
                val savedUser = gson.fromJson(savedJson, Map::class.java)
                val savedEmail = savedUser["email"] as? String
                val savedPassword = savedUser["password"] as? String

                if (email == savedEmail && password == savedPassword) {
                    Snackbar.make(rView, "Вход выполнен!", Snackbar.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainScreenActivity::class.java))
                    finish()
                } else {
                    Snackbar.make(rView, "Неверный email или пароль", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}