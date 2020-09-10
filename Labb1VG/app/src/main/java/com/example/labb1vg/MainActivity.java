package com.example.labb1vg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button plus, minus, multi, divide, equal, clear;
    TextView symbol;
    EditText numOne, numTwo, sum;
    Character operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        symbol = (TextView) findViewById(R.id.symbolText);
        sum = (EditText) findViewById(R.id.sumText);
        numOne = (EditText) findViewById(R.id.numberOne);
        numTwo = (EditText) findViewById(R.id.numberTwo);


        //Metod för plusknapp
        plus = (Button) findViewById(R.id.plusButton);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol.setText("+");
                operation = '+';
            }
        });

        //Metod för minus
        minus = (Button) findViewById(R.id.minusButton);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol.setText("-");
                operation = '-';
            }
        });
        //Metod för multiplikation
        multi = (Button) findViewById(R.id.multiButton);
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol.setText("*");
                operation = '*';
            }
        });
        //Metod för division
        divide = (Button) findViewById(R.id.divideButton);
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol.setText("/");
                operation = '/';
            }
        });
        //Metod för sum
        equal = (Button) findViewById(R.id.sumButton);
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(operation == '+'){
                    int nOne = Integer.parseInt(numOne.getText().toString());
                    int nTwo = Integer.parseInt(numTwo.getText().toString());
                    int res = nOne + nTwo;
                    sum.setText(Integer.toString(res));
                }
                if(operation == '-'){
                    int nOne = Integer.parseInt(numOne.getText().toString());
                    int nTwo = Integer.parseInt(numTwo.getText().toString());
                    int res = nOne - nTwo;
                    sum.setText(Integer.toString(res));
                }
                if(operation == '*'){
                    int nOne = Integer.parseInt(numOne.getText().toString());
                    int nTwo = Integer.parseInt(numTwo.getText().toString());
                    int res = nOne * nTwo;
                    sum.setText(Integer.toString(res));
                }
                if(operation == '/'){
                    int nOne = Integer.parseInt(numOne.getText().toString());
                    int nTwo = Integer.parseInt(numTwo.getText().toString());
                    if(nTwo==0){
                        Toast.makeText(getApplicationContext(), "Error!!", Toast.LENGTH_SHORT)
                                .show();
                    }
                    else{
                        int res = nOne / nTwo;
                        sum.setText(Integer.toString(res));
                    }
                }
            }
        });
        //Metod för att rensa fält
        clear = (Button) findViewById(R.id.clearButton);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol.setText("");
                numOne.setText("");
                numTwo.setText("");
                sum.setText("");
            }
        });
    }
}