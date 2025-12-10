package com.example.prack_2_epimakhovd.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.prack_2_epimakhovd.R
import com.google.android.material.snackbar.Snackbar
import data.AppDatabase
import data.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditTaskActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private var taskId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ✅ Правильное имя layout'а
        setContentView(R.layout.activity_add_activity)

        // Инициализация базы
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "tasks-db"
        ).build()

        // Меняем заголовок и кнопку
        findViewById<TextView>(R.id.title).text = "Редактировать задачу"
        findViewById<Button>(R.id.btnSave).text = "СОХРАНИТЬ ИЗМЕНЕНИЯ"

        val editTask = findViewById<EditText>(R.id.editTask)
        val btnSave = findViewById<Button>(R.id.btnSave)

        // Получаем данные
        taskId = intent.getIntExtra("TASK_ID", -1)
        val title = intent.getStringExtra("TASK_TITLE") ?: ""
        if (taskId == -1) {
            finish()
            return
        }

        editTask.setText(title)

        // Сохранение
        btnSave.setOnClickListener {
            val newText = editTask.text.toString().trim()
            if (newText.isEmpty()) {
                Snackbar.make(findViewById(R.id.rootLayout), "Введите текст задачи", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.IO).launch {
                db.taskDao().updateTask(Task(id = taskId, title = newText))

                withContext(Dispatchers.Main) {
                    setResult(RESULT_OK) // ← сообщает, что данные изменились
                    finish()
                }
            }
        }
    }


}