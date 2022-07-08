package com.keremkaratas.todoapp.view.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.keremkaratas.todoapp.R

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        if (auth.currentUser == null) {
            val intent=Intent(applicationContext, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
