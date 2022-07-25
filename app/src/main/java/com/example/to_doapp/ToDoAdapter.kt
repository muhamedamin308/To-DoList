package com.example.to_doapp

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.itemtodo.view.*

class ToDoAdapter(
    private val todos: MutableList<ToDO>
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    class ToDoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.itemtodo,
                parent,
                false
            )
        )
    }

    fun addToDO(todo : ToDO) {
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }
    fun deleteOnceDone(){
        todos.removeAll { todo ->
            todo.checked
        }
        notifyDataSetChanged()
    }

    private fun toggleThroght(tvToDO: TextView, isChecked : Boolean){
        if(isChecked){
            tvToDO.paintFlags = tvToDO.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else
            tvToDO.paintFlags = tvToDO.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentToDo = todos[position]
        holder.itemView.apply {
            tvToDOTitle.text = currentToDo.title
            cbDone.isChecked = currentToDo.checked
            toggleThroght(tvToDOTitle , currentToDo.checked)
            cbDone.setOnCheckedChangeListener {_ , isChecked ->
                toggleThroght(tvToDOTitle , isChecked)
                currentToDo.checked = !currentToDo.checked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

}