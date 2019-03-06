package com.inception.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;


    private ProgressBar progressBar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progress_bar);

        progressBar.setVisibility(View.INVISIBLE);

    }


    public void do_signup(View v)
    {




        EditText email_et = findViewById(R.id.email_et);

        EditText password_et = findViewById(R.id.password_et);



        String email = email_et.getText().toString();

        String password = password_et.getText().toString();


        if( email.isEmpty() && password.isEmpty() )
        {
            Toast.makeText(SignupActivity.this , "fill all fields" , Toast.LENGTH_SHORT).show();

            return;
        }

        progressBar.setVisibility(View.VISIBLE);



        OnCompleteListener<AuthResult> listener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressBar.setVisibility(View.INVISIBLE);

              Boolean bb =   task.isSuccessful();

              Boolean aa = task.isComplete();

              if( bb )
              {
                  Toast.makeText(SignupActivity.this , "successfuly registered" , Toast.LENGTH_SHORT).show();

                  finish();

              }
              else {

                  Toast.makeText(SignupActivity.this , "error try again" , Toast.LENGTH_SHORT).show();

              }

            }
        };


        mAuth.createUserWithEmailAndPassword(email , password).addOnCompleteListener(SignupActivity.this , listener);




    }


}
