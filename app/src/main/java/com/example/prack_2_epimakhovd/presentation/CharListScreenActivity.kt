package com.example.prack_2_epimakhovd.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.prack_2_epimakhovd.R
import data.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharListScreenActivity : AppCompatActivity() {
    private lateinit var backText: TextView
    private lateinit var adapter: TaskAdapter

    private lateinit var db: AppDatabase


    private lateinit var recyclerView: RecyclerView
    private lateinit var textEmpty: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list_screen)

        backText = findViewById(R.id.title)
        backText.setOnClickListener {
            startActivity(Intent(this, MainScreenActivity::class.java))
            finish()
        }



        recyclerView = findViewById(R.id.recyclerView)
        textEmpty = findViewById(R.id.textEmpty)


        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "tasks-db"
        ).build()

        adapter = TaskAdapter() { task ->
            CoroutineScope(Dispatchers.IO).launch {
                db.taskDao().deleteTask(task)
                refreshList()
            }
        }


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        refreshList()
    }

    private fun refreshList() {
        CoroutineScope(Dispatchers.IO).launch {
            val tasks = db.taskDao().getAllTasks()
            runOnUiThread {
                adapter.updateTasks(tasks)
                textEmpty.visibility = if (tasks.isEmpty()) View.VISIBLE else View.GONE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }
}