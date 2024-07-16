package com.example.databaseapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        val name=intent.getStringExtra(SignInActivity.key2)
        val userid=intent.getStringExtra(SignInActivity.key3)
        val email=intent.getStringExtra(SignInActivity.key1)

        val nameput=findViewById<TextView>(R.id.tVname)
        nameput.text=name.toString()
        val emailput=findViewById<TextView>(R.id.tVmail)
        emailput.text="Email: $email"
        val idput=findViewById<TextView>(R.id.tVuid)
        idput.text="ID: $userid"




    }
}