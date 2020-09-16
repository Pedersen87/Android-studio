package com.example.labb2g;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> words = new ArrayList<>();
    EditText randWord, lInput;
    String word;
    Button testButton, letterButton;
    String underscore, newWord;
    String newUnderscore = "";
    //char [] dash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        words.add("banana");
        words.add("moose");
        words.add("motorcycle");
        words.add("computer");
        words.add("purple");
        randWord = (EditText) findViewById(R.id.randomWord);
        testButton = (Button) findViewById(R.id.button);
        lInput = (EditText) findViewById(R.id.letterInput);
        letterButton = (Button) findViewById(R.id.checkLetterButton);

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.shuffle(words);
                word = words.get(0);
                words.remove(0);
                //underscore = new String(new char[word.length()]).replace("\0", "*"); //TEst
                underscore = word.replaceAll(".", "*");

                randWord.setText(underscore);
                Toast.makeText(getApplicationContext(), word, Toast.LENGTH_SHORT)
                        .show();
            }
        });

        letterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                for (int i = 0; i < word.length(); i++) {
                char dash[] = underscore.toCharArray();

                   if (word.charAt(i) == lInput.getText().charAt(0)) {
                        dash[i] = lInput.getText().charAt(0);
                        underscore = String.valueOf(dash);
                       //newUnderscore += lInput.getText().charAt(0);
                        randWord.setText(underscore);
                    }
                    /*if (underscore.charAt(i) != '*') {
                        newUnderscore += word.charAt(i);
                    } else {
                        newUnderscore += "*";
                    }*/
                }
                if(!underscore.contains("*")){
                        Toast.makeText(getApplicationContext(), "Vinst", Toast.LENGTH_LONG).show();
                }


            }
        });



    }
}