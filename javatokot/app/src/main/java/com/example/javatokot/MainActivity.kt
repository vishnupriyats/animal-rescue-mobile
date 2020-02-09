package com.example.javatokot

import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.image.image

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    // var mFullName
     //var mEmail
    //internal var mPassword
    //internal var mphone
    //internal var mRegisterBtn: Button
    //    TextView mLoginbtn;
    //internal var fAuth: FirebaseAuth

    //    ProgressBar progressBar;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mFullName = findViewById(R.id.fullName) as EditText
        var mEmail = findViewById(R.id.Email) as EditText
        var mPassword = findViewById(R.id.password) as EditText
        var mphone = findViewById(R.id.phone) as EditText
        var mRegisterBtn = findViewById(R.id.registerBtn) as Button
        //mLoginbtn=findViewById(R.id.createText);


        var fAuth = FirebaseAuth.getInstance()
        // progressBar = findViewById(R.id.progressBar);
        if (fAuth.currentUser != null) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
        mRegisterBtn.setOnClickListener(View.OnClickListener {
            val email = mEmail.text.toString().trim()
            val password = mPassword.text.toString().trim()
            if (TextUtils.isEmpty(email)) {
                mEmail.error = "Email is Required"
                return@OnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                mPassword.error = "password is required"
                return@OnClickListener
            }
            if (password.length < 6) {
                mPassword.error = "Password must be >=6 characters"
                return@OnClickListener
            }
            //                progressBar.setVisibility(View.VISIBLE);
            fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    Toast.makeText(this@MainActivity, "user  created:", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, image::class.java))
                    //                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(this@MainActivity, "error!" + task.exception!!.message, Toast.LENGTH_SHORT).show()


                }
            }
        })
    }
}




