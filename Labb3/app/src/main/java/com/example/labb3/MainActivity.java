package com.example.labb3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //private ArrayList<String> list = new ArrayList<>();
    private ArrayAdapter listAdapter;
    private EditText artistInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        artistInput = (EditText) findViewById(R.id.artistInput);
        Button searchButton = (Button) findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiService as = new ApiService(artistInput.getText().toString(), getApplicationContext());
                as.setArtistInput(artistInput.getText().toString());
                as.execute();
            }
        });
    }

    public void onResume() {
        super.onResume();
       if(getIntent().getStringArrayListExtra("similarartist") != null){
           listAdapter = new ArrayAdapter<String>(MainActivity.this,
                   android.R.layout.simple_list_item_1, getIntent().getStringArrayListExtra("similarartist"));
           ListView listView = (ListView) (findViewById(R.id.listArtist));
           listView.setAdapter(listAdapter);
       }
    }
}