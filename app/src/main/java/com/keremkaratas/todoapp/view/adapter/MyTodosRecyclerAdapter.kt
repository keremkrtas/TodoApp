package com.keremkaratas.todoapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.keremkaratas.todoapp.R
import com.keremkaratas.todoapp.databinding.MyTodosRowBinding
import com.keremkaratas.todoapp.view.model.ToDoModel
import com.squareup.picasso.Picasso

class MyTodosRecyclerAdapter(val list : ArrayList<ToDoModel>):RecyclerView.Adapter<MyTodosRecyclerAdapter.ViewHolder>() {

    class ViewHolder(val itemview:  MyTodosRowBinding) : RecyclerView.ViewHolder(itemview.root){
        var imageViewmTodos: ImageView = itemview.imageViewmTodos
        var textViewMtodosTitle :TextView = itemview.textViewMtodosTitle
        var textViewMtodosContent :TextView = itemview.textViewMtodosContent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //  val v = LayoutInflater.from(parent.context).inflate(R.layout.my_todos_row,parent,false)
        val inflater = LayoutInflater.from(parent.context)
        val binding : MyTodosRowBinding  = MyTodosRowBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      //  holder.textViewMtodosTitle.text = list[position].title
        holder.textViewMtodosContent.text = list[position].content
        Picasso.get().load(list[position].imgUrl).into(holder.imageViewmTodos)
        holder.itemview.mModel = list[position]
    }


    override fun getItemCount(): Int {
        return list.size
    }
}

