package com.example.labb2g;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> words = new ArrayList<>();
    TextView lifePoints;
    EditText randWord, lInput;
    String word, underscore;
    Button wordButton, letterButton, restartButton;
    int counter = 7;
    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        wordlibraryMethod();
        randWord = (EditText) findViewById(R.id.randomWord);
        wordButton = (Button) findViewById(R.id.button);
        lInput = (EditText) findViewById(R.id.letterInput);
        letterButton = (Button) findViewById(R.id.checkLetterButton);
        lifePoints = (TextView) findViewById(R.id.lifePoints);
        restartButton = (Button) findViewById(R.id.restartButton);
        letterButton.setVisibility(View.INVISIBLE);

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               restartMethod();
            }
        });

        wordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generatewordMethod();
            }
        });

        letterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lInput.getText().toString().length() != 0){
                    inputMethod();
                }
            }
        });


    }
        public void restartMethod(){
            randWord.setText("");
            lInput.setText("");
            lifePoints.setText("7");
            wordButton.setVisibility(View.VISIBLE);
            letterButton.setVisibility(View.INVISIBLE);
            counter = 7;
        }

        public void generatewordMethod(){
            Collections.shuffle(words);
            word = words.get(0);
            //words.remove(0);
            underscore = word.replaceAll(".", "_");
            randWord.setText(underscore);
            letterButton.setVisibility(View.VISIBLE);
        }

        public void inputMethod(){
            for (int i = 0; i < word.length(); i++) {
                char dash[] = underscore.toCharArray();
                if (word.charAt(i) == lInput.getText().charAt(0)) {
                    dash[i] = lInput.getText().charAt(0);
                    underscore = String.valueOf(dash);
                    randWord.setText(underscore);
                    check = true;
                }
            }

            if(check == false){
                counter --;
                lifePoints.setText(String.valueOf(counter));
            }
            if(!underscore.contains("_")){
                Toast.makeText(getApplicationContext(), "You are a winner!", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "To start a new game, press restart!", Toast.LENGTH_LONG).show();
                wordButton.setVisibility(View.INVISIBLE);
                letterButton.setVisibility(View.INVISIBLE);

            }
            if(counter <= 0){
                Toast.makeText(getApplicationContext(), "Looser!", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "To start a new game, press restart!", Toast.LENGTH_LONG).show();
                wordButton.setVisibility(View.INVISIBLE);
                letterButton.setVisibility(View.INVISIBLE);
            }
            check = false;
        }

        public void wordlibraryMethod(){
            words.add("monkey");
            words.add("banana");
            words.add("moose");
            words.add("motorcycle");
            words.add("computer");
            words.add("purple");
        }
}