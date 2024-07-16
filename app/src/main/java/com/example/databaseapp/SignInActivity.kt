package com.example.databaseapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {
    lateinit var database:DatabaseReference
    //making key
    companion object{
        const val key1="com.example.databaseapp.SignInActivity.key1"
        const val key2="com.example.databaseapp.SignInActivity.key2"
        const val key3="com.example.databaseapp.SignInActivity.key3"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)

        val loginButtton=findViewById<Button>(R.id.btnlogin)
        val etLoginId=findViewById<TextInputEditText>(R.id.loginUID)

        loginButtton.setOnClickListener {
            val userIDString=etLoginId.text.toString()
            if(userIDString.isNotEmpty()){
                readData(userIDString)   //creating a function that reads data from firebase
            }
            else{
                    Toast.makeText(this,"Please Enter Username",Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun readData(userIDString: String) {
        database= FirebaseDatabase.getInstance().getReference("Users")

        database.child(userIDString).get().addOnSuccessListener {
            if(it.exists()){
                //welcome user in the app and passing data
                val email=it.child("email").value
                val name=it.child("name").value
                val password=it.child("password").value
                val userid=it.child("uniqueID").value

                val intentHome= Intent(this,HomeActivity::class.java)
                intentHome.putExtra(key1,email.toString())
                intentHome.putExtra(key2,name.toString())
                intentHome.putExtra(key3,userid.toString())
                startActivity(intentHome)
            }
            else{
                Toast.makeText(this,"User not exists!",Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {   //jab db se data retrieve nahi kar pa rahe ho
            Toast.makeText(this,"Failed Technical Error",Toast.LENGTH_SHORT).show()
        }
    }
}