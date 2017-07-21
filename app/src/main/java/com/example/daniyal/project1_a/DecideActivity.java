package com.example.daniyal.project1_a;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class DecideActivity extends AppCompatActivity {

    int backButtonCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decide);
        AlertDialog.Builder builder = new AlertDialog.Builder(DecideActivity.this);
        builder.setTitle("Choose your activity to start");
        builder.setMessage("Select carefully you can not change or edit after");

        builder.setPositiveButton("Student",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent home = new Intent(getApplicationContext(), StudentActivity.class);
                startActivity(home);
                finish();
            }
        });

        builder.setNegativeButton("Company", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
               // Intent home = new Intent(getApplicationContext(), CompanyActivity.class);
                //startActivity(home);
                finish();
            }
        });
        AlertDialog alertdialog=builder.create();
        alertdialog.show();

    }



    @Override
    public void onBackPressed() {
        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            DecideActivity.this.finish();
        }
        else
        {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }
}
