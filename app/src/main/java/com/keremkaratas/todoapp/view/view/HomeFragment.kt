package com.keremkaratas.todoapp.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.keremkaratas.todoapp.R

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_home, container, false)
        val buttonNewTodo:Button= view.findViewById(R.id.buttonNewTodo)
        buttonNewTodo.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_createTodoFragment)
        }
        return view
    }
}