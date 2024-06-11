package com.naufalrf.agecalculator.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.naufalrf.agecalculator.R;
import java.util.Calendar;

public class AgeCalculationActivity extends AppCompatActivity {

    private TextInputEditText birthDateEditText;
    private TextView ageResultTextView;
    private Calendar birthDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_calculation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        birthDateEditText = findViewById(R.id.birthDateEditText);
        ageResultTextView = findViewById(R.id.ageResultTextView);

        birthDateEditText.setOnClickListener(v -> showDatePickerDialog());

        birthDate = Calendar.getInstance();
    }

    private void showDatePickerDialog() {
        int year = birthDate.get(Calendar.YEAR);
        int month = birthDate.get(Calendar.MONTH);
        int day = birthDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            birthDate.set(year1, month1, dayOfMonth);
            birthDateEditText.setText(String.format("%02d/%02d/%04d", dayOfMonth, month1 + 1, year1));
        }, year, month, day);

        datePickerDialog.show();
    }

    public void onCalculateAgeClick(View view) {
        if (birthDateEditText.getText().toString().isEmpty()) {
            ageResultTextView.setText("Silakan masukkan tanggal lahir yang valid");
            return;
        }

        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        ageResultTextView.setText("Usia Anda: " + age + " tahun");
        hideKeyboard(view);
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
