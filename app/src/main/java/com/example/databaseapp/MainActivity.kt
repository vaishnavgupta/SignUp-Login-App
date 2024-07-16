package com.example.databaseapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var database:DatabaseReference       //var named database of type DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnSignUp=findViewById<Button>(R.id.btnSignup)
        val pName=findViewById<TextInputEditText>(R.id.etName)
        val pMail=findViewById<TextInputEditText>(R.id.etMail)
        val pPassword=findViewById<TextInputEditText>(R.id.etPass)
        val pId=findViewById<TextInputEditText>(R.id.etID)
        val textlogin=findViewById<TextView>(R.id.tvlogin)

        textlogin.setOnClickListener{
            intent= Intent(applicationContext,SignInActivity::class.java)
            startActivity(intent)
        }

        btnSignUp.setOnClickListener {
            val name=pName.text.toString()   //toString because typeMismatch is happening
            val mail=pMail.text.toString()
            val pass=pPassword.text.toString()
            val id=pId.text.toString()
            //Order in Firebase:  Users-->id-->name,mail,password,id
            database=FirebaseDatabase.getInstance().getReference("Users")  //getInstance gives location of database
                                                                                //getReference give path upto users

            val user=User(name,mail,pass,id)  //creating a var user of User DataClass
            database.child(id).setValue(user).addOnSuccessListener { //user var mein jo value padi hai voh set ho jaye aur agar success ho jaye to ander vala karo
                pName.text?.clear()
                pMail.text?.clear()      //to  clear fields after they are entered
                pPassword.text?.clear()
                pId.text?.clear()
                Toast.makeText(this,"User Registered Successfully!!!",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {   //agar fail ho jaye
                Toast.makeText(this,"Failed!!!",Toast.LENGTH_SHORT).show()
            }

        }
    }
}