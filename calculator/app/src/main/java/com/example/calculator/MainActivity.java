package com.example.calculator;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity{

    String displayresult = "";
    double currentresult = 0;
    double currentinput = 0;
    String currentoperation = "";

    public void NumInput(String num, EditText res){
        displayresult += num;
        res.setText(displayresult);
        currentinput = Double.parseDouble(displayresult);
    }

    public void HandleOperation(String op,EditText result){
        if (!op.isEmpty() && !displayresult.isEmpty()) {
            PerformCalculation(result);
        } else if(!displayresult.isEmpty()) {
            currentresult = currentinput;
        }
        currentoperation = op;
        displayresult = "";
    }

    public void PerformCalculation(EditText result){
        switch (currentoperation){
            case "+":
                currentresult += currentinput;
                break;
            case "-":
                currentresult -= currentinput;
                break;
            case "x":
                currentresult *= currentinput;
                break;
            case "/":
                /*if(currentinput == 0){         // i could add the condition in case the user divides with Zero, but since it natively responds with infinity, i won't do the condition
                    result.setText("undefined");
                    currentresult = 0;
                    break;
                }*/
                currentresult /= currentinput;
                break;
            default:
                currentresult = currentinput;
        }
        displayresult = String.valueOf(currentresult);
        result.setText(displayresult);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.mix);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main4), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText result = findViewById(R.id.result);
        Button num0 = findViewById(R.id.zero);
        Button num1 = findViewById(R.id.one);
        Button num2 = findViewById(R.id.two);
        Button num3 = findViewById(R.id.three);
        Button num4 = findViewById(R.id.four);
        Button num5 = findViewById(R.id.five);
        Button num6 = findViewById(R.id.six);
        Button num7 = findViewById(R.id.seven);
        Button num8 = findViewById(R.id.eight);
        Button num9 = findViewById(R.id.nine);
        Button divide100 = findViewById(R.id.divide100);
        Button minus = findViewById(R.id.minus);
        Button plus = findViewById(R.id.plus);
        Button multiply = findViewById(R.id.multiply);
        Button divide = findViewById(R.id.divide);
        Button reset = findViewById(R.id.reset);
        Button equals = findViewById(R.id.equals);
        Button delete = findViewById(R.id.delete);
        Button point = findViewById(R.id.point);


        plus.setOnClickListener(v -> HandleOperation("+",result));
        minus.setOnClickListener(v -> HandleOperation("-",result));
        multiply.setOnClickListener(v -> HandleOperation("x",result));
        divide.setOnClickListener(v -> HandleOperation("/",result));

        divide100.setOnClickListener(v -> {
            currentinput = Double.parseDouble(displayresult) / 100;
            displayresult = String.valueOf(currentinput);
            result.setText(displayresult);
        });

        delete.setOnClickListener(v -> {
            if(displayresult.length() == 1){
                displayresult = "";
                currentoperation = "";
                result.setText(String.valueOf(currentresult));
            }else if(displayresult.length() > 1){
                displayresult = displayresult.substring(0,displayresult.length()-1);
                result.setText(displayresult);
                currentinput = Double.parseDouble(displayresult);
            }
        });

        equals.setOnClickListener(v -> {
            if(!currentoperation.isEmpty()){
                PerformCalculation(result);
                result.setText(String.valueOf(currentresult));
                displayresult = "";
                currentoperation = "";
            }
        });

        reset.setOnClickListener(v -> {
            displayresult = "";
            currentoperation = "";
            currentresult = 0;
            currentinput = 0;
            result.setText(displayresult);
        });

        num1.setOnClickListener(v -> NumInput("1",result));
        num2.setOnClickListener(v -> NumInput("2",result));
        num3.setOnClickListener(v -> NumInput("3",result));
        num4.setOnClickListener(v -> NumInput("4",result));
        num5.setOnClickListener(v -> NumInput("5",result));
        num6.setOnClickListener(v -> NumInput("6",result));
        num7.setOnClickListener(v -> NumInput("7",result));
        num8.setOnClickListener(v -> NumInput("8",result));
        num9.setOnClickListener(v -> NumInput("9",result));
        num0.setOnClickListener(v -> NumInput("0",result));
        point.setOnClickListener(v -> {
            if(displayresult.isEmpty()){
                NumInput("0",result);
                NumInput(".",result);
            }else if (!displayresult.contains(".")){
                NumInput(".", result);
            }
        });

    }

}