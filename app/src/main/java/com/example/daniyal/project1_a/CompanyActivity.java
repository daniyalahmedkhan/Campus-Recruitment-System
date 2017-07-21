package com.example.daniyal.project1_a;

import android.app.ProgressDialog;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CompanyActivity extends AppCompatActivity {

    EditText name , adress , about;
    Button BtnSave;
    String type = "company";
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        name = (EditText) findViewById(R.id.EditCompName);
        adress = (EditText) findViewById(R.id.EditCompAdd);
        about = (EditText) findViewById(R.id.EditCompBio);
        BtnSave = (Button) findViewById(R.id.BtnCompSubmit);
        progressDialog = new ProgressDialog(this);
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
        String Add = adress.getText().toString().trim();
        String About = about.getText().toString().trim();
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
        ModelClass Mod = new ModelClass(Name , Add , About , type);
        databaseReference.child(LoginActivity.UID).setValue(Mod, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseReference.equals(databaseError)){
                    Toast.makeText(CompanyActivity.this , "Failed in saving" , Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }else {

                    Toast.makeText(CompanyActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
                }
        });


    }
}
