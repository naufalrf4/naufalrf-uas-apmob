package com.naufalrf.agecalculator.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.naufalrf.agecalculator.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Delay for 3 seconds
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}
