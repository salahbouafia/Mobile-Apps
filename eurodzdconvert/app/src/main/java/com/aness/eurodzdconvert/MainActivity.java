package com.aness.eurodzdconvert;



import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber2;
    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2;
    private Button convertButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mixof_layouts);


        editTextNumber2 = findViewById(R.id.editTextNumber2);
        radioGroup = findViewById(R.id.radiogrp1);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        convertButton = findViewById(R.id.button2);
        resultText = findViewById(R.id.resultText);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                String inputValue = editTextNumber2.getText().toString();

                if (inputValue.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();
                    return;
                }
                double value = Double.parseDouble(inputValue);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == radioButton1.getId()) {
                    double result = value / 260;
                    resultText.setText(String.format("%.2f DZD = %.2f Euro", value, result));
                } else if (selectedId == radioButton2.getId()) {
                    double result = value * 260;
                    resultText.setText(String.format("%.2f Euro = %.2f DZD", value, result));
                } else {
                    Toast.makeText(MainActivity.this, "Please select a conversion option", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
