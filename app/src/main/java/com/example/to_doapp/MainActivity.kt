package com.example.to_doapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var toDoAdapter:ToDoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "To-Do List"
        toDoAdapter = ToDoAdapter(mutableListOf())
        recyclerview.adapter = toDoAdapter
        recyclerview.layoutManager = LinearLayoutManager(this)
        btnAddToDo.setOnClickListener {
            val title = etToDoTitle.text.toString()
            if (title.isNotBlank()){
                val todo = ToDO(title)
                toDoAdapter.addToDO(todo)
                etToDoTitle.text.clear()
            }
        }
        btnRemoveToDo.setOnClickListener {
            toDoAdapter.deleteOnceDone()
        }
    }
}