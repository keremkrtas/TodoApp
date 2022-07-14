package com.keremkaratas.todoapp.view.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.keremkaratas.todoapp.R
import com.keremkaratas.todoapp.databinding.FragmentHomeBinding
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        var view = binding.root
        Picasso
            .get()
            .load("https://www.mavibilgiler.com/wp-content/uploads/2020/05/market-listesi.-1.jpg")
            .into(binding.imageViewHome)
        val buttonNewTodo:Button= view.findViewById(R.id.buttonNewTodo)
     binding.button3.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_myTodosFragment)
        }
        binding.buttonNewTodo.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_createTodoFragment)
        }
        return view

    }

}