package com.example.studentprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    protected Button button = null;
    protected SharedPreferenceHelper sharedPreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferenceHelper = new SharedPreferenceHelper(MainActivity.this);

        // Change Action Bar title
        getSupportActionBar().setTitle("MyProfile");

        // Get ID of button
        button = (Button) findViewById(R.id.profileButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfileActivity();
            }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        String name = sharedPreferenceHelper.getProfileName();
        if (name == null) {
            goToProfileActivity();
        } else {
            button.setText(name);
        }
    }


    void goToProfileActivity(){
        Intent intent = new Intent(MainActivity.this, profileActivity.class);
        startActivity(intent);
    }

}