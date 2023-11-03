package com.example.todoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.Task

class TaskCompleteAdapter (private val context: Context, private val list: List<Task>):
    RecyclerView.Adapter<RecyclerView.ViewHolder> ()  {
    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_task_complete, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var task=list[position]

        if(holder is MyViewHolder){
            holder.rb_textview.text=task.title

            holder.undo_btn.setOnClickListener{
                if(onClickListener!=null){
                    onClickListener!!.onDoneClick(task)
                }
            }

            holder.delete_btn.setOnClickListener{
                if(onClickListener!=null){
                    onClickListener!!.onDeleteClick(task)
                }
            }





        }
    }

    class MyViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val rb_textview=view.findViewById<TextView>(R.id.item_task_complete)
        val undo_btn=view.findViewById<ImageView>(R.id.icon_undo)
        val delete_btn=view.findViewById<ImageView>(R.id.icon_delete)

    }

    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener=onClickListener
    }

    interface OnClickListener{
        fun onDoneClick(task:Task)
        fun onDeleteClick(model:Task)

    }
}