package com.keremkaratas.todoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.keremkaratas.todoapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()
    }

    fun signIn(view: View) {
        email = binding.editTextTextEmailAddress.text.toString()
        password = binding.editTextTextPassword.text.toString()
        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
            Log.e("******signin**",it.user!!.email.toString())
        }.addOnFailureListener {
            Log.e("*********",it.localizedMessage)
        }

    }

    fun signUp(view: View) {

        email = binding.editTextTextEmailAddress.text.toString()
        password = binding.editTextTextPassword.text.toString()
        auth.createUserWithEmailAndPassword(email,password)
    }
}