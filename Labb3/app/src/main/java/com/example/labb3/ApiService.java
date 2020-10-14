package com.example.labb3;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class ApiService extends AsyncTask<String, Void, String> {
    private ArrayList<String> list = new ArrayList<>();
    private String getSimilar;
    private String artistInput;
    MainActivity m = new MainActivity();

        public ApiService(String artistInput){
                this.artistInput = artistInput;
        }

        public void setArtistInput(String artistInput){
               this.artistInput = artistInput;
        }

        public String getArtistInput(){
                return artistInput;
        }

        @Override
        protected String doInBackground(String... strings) {

            String artistSearch = getArtistInput();
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
                        }
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
        System.out.println("Liknande artister: " + Arrays.toString(list.toArray()));

        /* listAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, list);
        ListView listView = (ListView) (findViewById(R.id.listArtist));
        listView.setAdapter(listAdapter);*/
        super.onPostExecute(s);
    }
    }

