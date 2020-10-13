package com.example.labb3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity  {
    private ArrayList<String> list = new ArrayList<>();
    private ArrayAdapter listAdapter;
    private EditText artistInput;
    private String getSimilar;
    ProgressDialog pD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        artistInput = (EditText) findViewById(R.id.artistInput);
        Button searchButton = (Button) findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               AsyncTaskApi ata = new AsyncTaskApi();
               ata.execute();
            }
        });
    }
            private class AsyncTaskApi extends AsyncTask<Void, Void, String>{

                /*@Override
                protected void onPreExecute(){
                    pD = new ProgressDialog(MainActivity.this);
                    pD.setMessage("Loading...");
                    pD.setIndeterminate(false);
                    pD.setCancelable(false);
                    pD.show();
                }*/

                @Override
                protected String doInBackground(Void... voids) {
                    String artistSearch = artistInput.getText().toString();
                    list = new ArrayList<>();
                    URL url;
                    for(int i =0; i<1; i++){
                        try{
                            url = new URL("http://ws.audioscrobbler.com/2.0/?method=artist.getsimilar&artist="+artistSearch+"&limit=5&api_key=7da7759f316358f0712e089cc08bd518");
                            Log.d("Eservice", "Calling URL: "+url.toString());
                            XmlPullParserFactory parserCreator = XmlPullParserFactory
                                    .newInstance();
                            XmlPullParser parser = parserCreator.newPullParser();
                            parser.setInput(url.openStream(), null);
                            int parserEvent = parser.getEventType();
                            String tagName;
                            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                                if (parserEvent==XmlPullParser.START_TAG) {
                                    tagName = parser.getName();
                                    Log.d("Eservice", "Start tag found: "+tagName);

                                    if(tagName.equals("name")){
                                        parser.next();
                                        getSimilar = parser.getText();
                                        list.add(getSimilar);
                                    }
                                } //Eventuellt nedanför nedanstående rad
                                    parserEvent = parser.next();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(String s) {
                    listAdapter = new ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_list_item_1, list);
                    ListView listView = (ListView) (findViewById(R.id.listArtist));
                    listView.setAdapter(listAdapter);
                    super.onPostExecute(s);
                }
            }








}
