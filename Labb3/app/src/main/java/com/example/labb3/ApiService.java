package com.example.labb3;

import android.app.IntentService;
import android.content.Context;
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

public class ApiService extends AsyncTask<ArrayList<String>, Void, ArrayList<String>> {
    private ArrayList<String> list = new ArrayList<>();
    private String getSimilar;
    private String artistInput;
    private Context c;
    MainActivity m = new MainActivity();

        public ApiService(String artistInput, Context cIn){
                this.artistInput = artistInput;
                this.c = cIn;
        }

        public void setArtistInput(String artistInput){
               this.artistInput = artistInput;
        }

        public String getArtistInput(){
                return artistInput;
        }

        @Override
        protected ArrayList<String> doInBackground(ArrayList<String>... p) {

            String artistSearch = getArtistInput();
            list = new ArrayList();
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
            return list;
        }


    @Override
    protected void onPostExecute(ArrayList<String> list) {
        super.onPostExecute(list);
        Intent back = new Intent(c, MainActivity.class);
        back.putStringArrayListExtra("similarartist", list);
        back.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(back);

        System.out.println("Liknande artister: " + Arrays.toString(list.toArray()));

    }

}

