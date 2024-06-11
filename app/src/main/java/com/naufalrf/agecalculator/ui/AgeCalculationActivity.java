package com.naufalrf.agecalculator.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.naufalrf.agecalculator.R;
import java.util.Calendar;

public class AgeCalculationActivity extends AppCompatActivity {

    private EditText birthYearEditText;
    private TextView ageResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_calculation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        birthYearEditText = findViewById(R.id.birthYearEditText);
        ageResultTextView = findViewById(R.id.ageResultTextView);

        birthYearEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                showKeyboard(v);
            }
        });
    }

    public void onCalculateAgeClick(View view) {
        String birthYearStr = birthYearEditText.getText().toString();
        if (!birthYearStr.isEmpty()) {
            int birthYear = Integer.parseInt(birthYearStr);
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int age = currentYear - birthYear;
            ageResultTextView.setText("Usia Anda sekarang adalah " + age + " tahun");
            hideKeyboard(view);
        } else {
            ageResultTextView.setText("Harap masukkan tahun lahir Anda");
        }
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }
}
