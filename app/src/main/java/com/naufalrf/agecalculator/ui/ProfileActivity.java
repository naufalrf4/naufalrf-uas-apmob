package com.naufalrf.agecalculator.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.naufalrf.agecalculator.R;

public class ProfileActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_MODE = "dark_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Load theme based on saved preference
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean darkMode = preferences.getBoolean(PREF_DARK_MODE, false);
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SwitchMaterial themeSwitch = findViewById(R.id.themeSwitch);
        themeSwitch.setChecked(darkMode);
        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(PREF_DARK_MODE, isChecked);
            editor.apply();

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
    }

    public void onCalculateAgeClick(View view) {
        Intent intent = new Intent(ProfileActivity.this, AgeCalculationActivity.class);
        startActivity(intent);
    }
}
