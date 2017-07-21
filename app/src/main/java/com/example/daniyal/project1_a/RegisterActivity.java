package com.example.daniyal.project1_a;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText E1 , E2;
    Button Btn1;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        E1 = (EditText) findViewById(R.id.RegEmail);
        E2 = (EditText) findViewById(R.id.RegPass);
        Btn1 = (Button) findViewById(R.id.RegButton);
        Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegData();
            }
        });

    }

    public void RegData(){


        String email = E1.getText().toString().trim();
        String pass = E2.getText().toString().trim();
        progressDialog.setMessage("Please Wait..");
        progressDialog.show();
        if (!(TextUtils.isEmpty(email) && TextUtils.isEmpty(pass))){
            if(email.endsWith("@gmail.com") || email.endsWith("@yahoo.com")){

                firebaseAuth.createUserWithEmailAndPassword(email , pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this , " Successfully Registered" , Toast.LENGTH_SHORT).show();
                    }
                });


            }else {

                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this , "Registration failed" , Toast.LENGTH_SHORT).show();
            }



        }else {

            progressDialog.dismiss();
            Toast.makeText(RegisterActivity.this , "Enter Fields" , Toast.LENGTH_SHORT).show();
        }



    }
}
