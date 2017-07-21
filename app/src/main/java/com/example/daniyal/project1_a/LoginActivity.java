package com.example.daniyal.project1_a;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText E1 , E2;
    Button Btn1;
    TextView t1;
    static String UID;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    DatabaseReference firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference("users");
        progressDialog = new ProgressDialog(this);
        E1 = (EditText) findViewById(R.id.LoginEmail);
        E2 = (EditText) findViewById(R.id.LoginPass);
        Btn1 = (Button) findViewById(R.id.LoginBtn);
        t1 = (TextView) findViewById(R.id.GotoReg);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
    }

    public void Login(){
        String Email = E1.getText().toString().trim();
        String Pass = E2.getText().toString().trim();

        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(Email , Pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                Toast.makeText(LoginActivity.this , "Successfully login" , Toast.LENGTH_LONG).show();

                UID =  firebaseAuth.getCurrentUser().getUid();
                CheckUser();

            }
        });

    }
    public void CheckUser(){

        firebaseDatabase.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {

                    Model mod = dataSnapshot.getValue(Model.class);
                    String type = mod.type;
                    Toast.makeText(LoginActivity.this, "..." + type , Toast.LENGTH_SHORT).show();
                }else {

                    Intent intent = new Intent(LoginActivity.this , StudentActivity.class);
                    startActivity(intent);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
