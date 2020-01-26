package com.example.locationremindersystem;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText mEmail,mPassword;
    Button mLoginbtn;
    TextView mCreatebtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail      = findViewById(R.id.email);
        mPassword   = findViewById(R.id.password);
        mLoginbtn   = findViewById(R.id.loginbtn);
        mCreatebtn  = findViewById(R.id.gotocreatAccount);
        progressBar = findViewById(R.id.progressBar2);

         mLoginbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String email = mEmail.getText().toString().trim();
                 String password = mPassword.getText().toString().trim();

                 if (TextUtils.isEmpty(email)){
                     mEmail.setError("Email is Required.");
                     return;
                 }

                 if (TextUtils.isEmpty(password)){
                     mPassword.setError("password is required.");
                     return;
                 }
                 if (password.length()< 6){
                     mPassword.setError("password must be more then six characters");
                     return;
                 }

                 progressBar.setVisibility(View.VISIBLE);


                 fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {

                         if (task.isSuccessful()){
                             Toast.makeText(Login.this,"Successfully Logged Id", Toast.LENGTH_SHORT).show();
                             startActivity(new Intent(getApplicationContext(),MainActivity.class));
                         }else
                         {
                             Toast.makeText(Login.this,"Error " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                             progressBar.setVisibility(View.INVISIBLE);
                         }
                     }
                 });
             }
         });




        mCreatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });


    }
    }

