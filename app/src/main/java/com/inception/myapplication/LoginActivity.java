package com.inception.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

    }

    public void open_signup(View view) {

        Intent i = new Intent(LoginActivity.this  , SignupActivity.class);

        startActivity(i);
    }

    public void do_login(View view) {


        EditText email_et = findViewById(R.id.email_et);

        EditText password_et = findViewById(R.id.password_et);



        String email = email_et.getText().toString();

        String password = password_et.getText().toString();


        if( email.isEmpty() && password.isEmpty() )
        {
            Toast.makeText(LoginActivity.this , "fill all fields" , Toast.LENGTH_SHORT).show();

            return;
        }

        OnCompleteListener<AuthResult> listener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this , "LOGGED IN SUCCESSFULLY" , Toast.LENGTH_SHORT).show();
                }

                else {

                    Toast.makeText(LoginActivity.this , "INVALID CREDENTIALS" , Toast.LENGTH_SHORT).show();

                }

            }
        };


        auth.signInWithEmailAndPassword(email , password).addOnCompleteListener(LoginActivity.this , listener);

    }
}
