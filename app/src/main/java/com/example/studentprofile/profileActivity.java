package com.example.studentprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Objects;

public class profileActivity extends AppCompatActivity {

    EditText name, age, sid;
    Button saveButton;
    boolean isEdit = false;
    String nameStr, ageStr, sidStr;
    protected SharedPreferenceHelper sharedPreferenceHelper;

    // Add the menu item on App bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.grade_meny, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.editButton:
                isEdit = !isEdit;
                if (isEdit){
                    name.setFocusableInTouchMode(isEdit);
                    age.setFocusableInTouchMode(isEdit);
                    sid.setFocusableInTouchMode(isEdit);
                    saveButton.setVisibility(View.VISIBLE);
                }else {
                    name.setFocusable(isEdit);
                    age.setFocusable(isEdit);
                    sid.setFocusable(isEdit);
                    saveButton.setVisibility(View.INVISIBLE);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Adding back button...
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Rename ActionBar title
        getSupportActionBar().setTitle("ProfileActivity");

        // Identify the ID
        name = findViewById(R.id.nameEditText);
        age = findViewById(R.id.ageEditText);
        sid = findViewById(R.id.sidEditText);
        saveButton = findViewById(R.id.saveButton);

        sharedPreferenceHelper = new SharedPreferenceHelper(profileActivity.this);

        name.setFocusable(isEdit);
        age.setFocusable(isEdit);
        sid.setFocusable(isEdit);
        saveButton.setVisibility(View.INVISIBLE);

        name.setText(sharedPreferenceHelper.getProfileName());
        age.setText(sharedPreferenceHelper.getProfileAge());
        sid.setText(sharedPreferenceHelper.getProfileSID());


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameStr = name.getText().toString();
                ageStr = age.getText().toString();
                sidStr = sid.getText().toString();


                if (nameStr.isEmpty()) {
                    sharedPreferenceHelper.saveProfileName(null, null, null);
                    Toast.makeText(profileActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                }
                else if (ageStr.isEmpty())
                    Toast.makeText(profileActivity.this, "Enter Age", Toast.LENGTH_SHORT).show();
                else if (Integer.parseInt(ageStr) >= 18){
                     if (sidStr.isEmpty())
                        Toast.makeText(profileActivity.this, "Enter SID", Toast.LENGTH_SHORT).show();
                     else{
                         sharedPreferenceHelper.saveProfileName(nameStr,ageStr,sidStr);
                         Toast.makeText(profileActivity.this, "Profile Saved", Toast.LENGTH_SHORT).show();
                     }
                }
                else
                    Toast.makeText(profileActivity.this, "Invalid Age", Toast.LENGTH_SHORT).show();


            }
        });

    }
}