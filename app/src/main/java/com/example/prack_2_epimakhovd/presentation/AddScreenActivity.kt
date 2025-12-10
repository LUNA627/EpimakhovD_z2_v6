package com.example.prack_2_epimakhovd.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.room.CoroutinesRoom
import com.example.prack_2_epimakhovd.R
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import data.AppDatabase
import data.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddScreenActivity : ComponentActivity() {

    private lateinit var textBack: TextView

    private lateinit var editTask: EditText
    private lateinit var btnSave: Button

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_activity)

        try {

            textBack = findViewById(R.id.title)
            textBack.setOnClickListener {
                startActivity(Intent(this, MainScreenActivity::class.java))
                finish()
            }



            db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "tasks-db"
            ).build()


            editTask = findViewById(R.id.editTask)
            btnSave = findViewById(R.id.btnSave)

            btnSave.setOnClickListener {
                val text = editTask.text.toString()
                if (text.isEmpty()) {
                    Snackbar.make(
                        findViewById(R.id.rootLayout),
                        "Напишите задачу",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                val id = (System.currentTimeMillis() % 100000).toInt()

                CoroutineScope(Dispatchers.IO).launch {
                    db.taskDao().insertTask(Task(id = id, title = text))

                    withContext(Dispatchers.Main) {
                        Snackbar.make(
                            findViewById(R.id.rootLayout),
                            "Задача сохранена",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                }
            }

        }
        catch (e: Exception) {
            e.printStackTrace() // выведет в Logcat
            Toast.makeText(this, "Ошибка: ${e.message}", Toast.LENGTH_LONG).show()
        }


    }
}
