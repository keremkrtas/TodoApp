package com.keremkaratas.todoapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.keremkaratas.todoapp.R
import com.keremkaratas.todoapp.databinding.FragmentCreateTodoBinding


class CreateTodoFragment : Fragment() {
    private var _binding: FragmentCreateTodoBinding? = null
    private val binding get() = _binding!!
    private val PICK_IMAGE_REQUEST = 71
    lateinit var launcher: ActivityResultLauncher<String>
    val TAG = "****"
    val database = Firebase.firestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateTodoBinding.inflate(inflater, container, false)
        var view = binding.root
        val firebaseStore = FirebaseStorage.getInstance()
        val storageReference = FirebaseStorage.getInstance().reference
        launcher = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri ->
            binding.imageView.setImageURI(uri)
            val reference = FirebaseStorage.getInstance().reference.child("image")
            reference.putFile(uri!!).addOnSuccessListener {

                reference.downloadUrl.addOnSuccessListener { downloadurl ->

                    var map = mapOf("uri" to downloadurl.toString())
                    Log.e(TAG, downloadurl.toString())

                    database.collection("users")
                        .add(map)
                        .addOnSuccessListener { documentReference ->
                            Log.e(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                            Toast.makeText(activity, "Başarılı", Toast.LENGTH_LONG).show()
                        }
                        .addOnFailureListener { e ->
                            Log.e(TAG, "Error adding document", e)
                            Toast.makeText(activity, "Başarısız", Toast.LENGTH_LONG).show()

                        }
                }
            }
        }

        binding.buttonSelectImg.setOnClickListener { launcher.launch("image/*") }
        return view
    }

    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

    }


}