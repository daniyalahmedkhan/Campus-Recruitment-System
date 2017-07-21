package com.example.daniyal.project1_a;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentActivity extends AppCompatActivity {

    EditText name, email, cgpa, age, bio;
    String type = "student";
    Button BtnSave;
    int backButtonCount;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        progressDialog = new ProgressDialog(this);
        name = (EditText) findViewById(R.id.EditFullName);
        email = (EditText) findViewById(R.id.EditEmail);
        cgpa = (EditText ) findViewById(R.id.EditCGPA);
        age = (EditText ) findViewById(R.id.EditAge);
        bio = (EditText ) findViewById(R.id.EditBio);
        BtnSave = (Button) findViewById(R.id.ButtonSubmit);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveData();
            }
        });
    }

    public void SaveData(){
        String Name = name.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String CGPA = cgpa.getText().toString().trim();
        String AGE = age.getText().toString().trim();
        String BIO = bio.getText().toString().trim();
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        ModelClass Mod = new ModelClass(Name , Email , CGPA , AGE , BIO , type);
        databaseReference.child(LoginActivity.UID).setValue(Mod, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseReference.equals(databaseError)){
                    Toast.makeText(StudentActivity.this , "Failed in saving" , Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }else {

                    Toast.makeText(StudentActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }

            }
        });


    }

    @Override
    public void onBackPressed() {
        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            StudentActivity.this.finish();
        }
        else
        {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }
}
