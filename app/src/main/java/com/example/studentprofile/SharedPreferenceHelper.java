package com.example.studentprofile;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelper {

    private SharedPreferences sharedPreferences;

    public SharedPreferenceHelper(Context context)
    {
        sharedPreferences = context.getSharedPreferences("ProfilePreference", Context.MODE_PRIVATE );
    }
    public void saveProfileName(String name, String age, String sid)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("profileName",name);
        editor.putString("profileAge", age);
        editor.putString("profileSID", sid);
        editor.commit();
    }
    public String getProfileName()
    {
        return sharedPreferences.getString("profileName", null);
    }

    public String getProfileAge(){
        return sharedPreferences.getString("profileAge", null);
    }

    public String getProfileSID(){
        return sharedPreferences.getString("profileSID", null);
    }

}
