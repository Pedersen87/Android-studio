package com.example.labb2g;

import java.util.ArrayList;
import java.util.Collections;

public class Wordlist {
    ArrayList<String> words = new ArrayList<>();
    String word;
//Lägg till randommetod
    public void wordlibraryMethod(){
        words.add("monkey");
        words.add("banana");
        words.add("moose");
        words.add("motorcycle");
        words.add("computer");
        words.add("purple");
        Collections.shuffle(words); //test
        word = words.get(0);
    }
}
