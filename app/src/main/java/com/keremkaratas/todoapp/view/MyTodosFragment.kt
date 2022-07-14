package com.keremkaratas.todoapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.keremkaratas.todoapp.R
import com.keremkaratas.todoapp.databinding.FragmentMyTodosBinding
import com.keremkaratas.todoapp.view.adapter.MyTodosRecyclerAdapter
import com.keremkaratas.todoapp.view.model.ToDoModel

class MyTodosFragment : Fragment() {
    val TAG = "*****"
    lateinit var currentUser: String
    val database = Firebase.firestore
    private var _binding: FragmentMyTodosBinding? = null
    private var mArrayList = ArrayList<ToDoModel>()
    private val binding get() = _binding!!
    private var layoutmanager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<MyTodosRecyclerAdapter.ViewHolder>? = null
    var list: ArrayList<ToDoModel> = ArrayList()
    val todoIdList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyTodosBinding.inflate(inflater, container, false)
        currentUser = FirebaseAuth.getInstance().currentUser!!.uid

        database.collection("users").document(currentUser).collection("todos").get()
            .addOnCompleteListener { task ->
                val result = task.result
                result?.let {
                    result.forEach {
                        database.collection("todos").document(it.id).get().addOnSuccessListener { task ->
                            Log.e("****000**", task.id)


                        }

                        todoIdList.add(it.id)

       todoIdList.forEach { a ->
            var docRef =
                database.collection("todos").document(a).get().addOnSuccessListener { task ->
                    Log.e("****1111**", task.id)

                    val todo = task.toObject<ToDoModel>()
                    if (!list.contains(todo)){
                        Log.e("**3333****", todo!!.title!!)
                        list.add(todo)
                        Log.e("***********size*****",list.size.toString())
                        if (list.size==todoIdList.size){
                            list.forEach {
                                Log.e("tttttt0tttt",it.title)
                                Log.e("tttttt0tttt",it.content)
                                Log.e("tttttt0tttt",it.imgUrl)
                                Log.e("tttttt0tttt",it.id)
                                Log.e("tttttt0tttt",it.publisherId)
                                Log.e("tttttt0tttt",it.LikesCount.toString())
                                adapter!!.notifyDataSetChanged()
                            }
                        }
                    }

                }
           Log.e("***2222***",todoIdList.size.toString())

       }
                    }
                }
            }


        var view = binding.root
     /*   var b = ToDoModel(
            "dad",
            "faa",
            "title2",
            "content2",
            "https://www.arover.net/wp-content/uploads/2021/12/Elon-Musk-Argues-Dogecoin-DOGE-Is-Ideal-For-Trading-Over.jpg",
            1,
            ArrayList()
        )


        list.add(b) */
        layoutmanager = LinearLayoutManager(this.context)
        binding.rvmTodos.layoutManager = layoutmanager
        adapter = MyTodosRecyclerAdapter(list)
        binding.rvmTodos.adapter = adapter
        return view
    }


}