package com.keremkaratas.todoapp.view.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.keremkaratas.todoapp.R
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.keremkaratas.todoapp.databinding.FragmentCreateTodoBinding
import com.keremkaratas.todoapp.view.model.ToDoModel
import java.util.*


class CreateTodoFragment : Fragment() {
    private var _binding: FragmentCreateTodoBinding? = null
    private val binding get() = _binding!!
    private val PICK_IMAGE_REQUEST = 71
    lateinit var reference: StorageReference
    lateinit var currentUserUid: String
    lateinit var launcher: ActivityResultLauncher<String>
    val TAG = "****"
    val database = Firebase.firestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateTodoBinding.inflate(inflater, container, false)
        var view = binding.root
        definition()
        binding.buttonSelectImg.setOnClickListener { launcher.launch("image/*") }
        return view
    }

    fun definition() {
        val firebaseStore = FirebaseStorage.getInstance()
        val storageReference = FirebaseStorage.getInstance().reference
        currentUserUid = FirebaseAuth.getInstance().currentUser!!.uid
        launcher = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri ->
            binding.imageView.setImageURI(uri)
            reference = FirebaseStorage.getInstance().reference.child("image${UUID.randomUUID()}")
            reference.putFile(uri!!).addOnSuccessListener {
                binding.buttonUpload.isClickable = true
            }
            binding.buttonUpload.setOnClickListener {
                addtoFirestoreTodosCollections()
            }
        }
    }
    fun addtoFirestoreUsersCollections(todoUID: String) {

            var map = mapOf("todoId" to todoUID)
            val arrrayList = ArrayList<String>()


            database.collection("users").document(currentUserUid).collection("todos").document(todoUID)
                .set(map)
                .addOnSuccessListener { documentReference ->
                    //   Log.e(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    Toast.makeText(activity, "Başarılı", Toast.LENGTH_LONG).show()
                    Navigation.findNavController(view!!).navigate(R.id.action_createTodoFragment_to_homeFragment)
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Error adding document", e)
                    Toast.makeText(activity, "Başarısız", Toast.LENGTH_LONG).show()

                }


    }
    fun addtoFirestoreTodosCollections() {

        reference.downloadUrl.addOnSuccessListener { downloadUrl ->

            var map = mapOf("photo_url" to downloadUrl.toString())
            val arrrayList = ArrayList<String>()
            val todoUID = UUID.randomUUID().toString()
            var todo = ToDoModel(
                todoUID,
                currentUserUid,
                binding.editTextTitle.text.toString(),
                binding.editTextContent.text.toString(),
                downloadUrl.toString(),
                0,
                arrrayList
            )
            Log.e(TAG, downloadUrl.toString())

            database.collection("todos").document(todoUID)
                .set(todo)
                .addOnSuccessListener { documentReference ->
                    //   Log.e(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    Toast.makeText(activity, "Başarılı", Toast.LENGTH_LONG).show()
                    addtoFirestoreUsersCollections(todoUID)
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Error adding document", e)
                    Toast.makeText(activity, "Başarısız", Toast.LENGTH_LONG).show()

                }
        }
    }
}