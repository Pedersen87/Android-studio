package com.example.labb4;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private TextView responseField;
    private ArrayList<String> list ,listSavedMovies = new ArrayList<>();
    private ArrayAdapter aa, movieSavedAdapter;
    private EditText nom;
    private JSONObject data, title, similarTitle;
    private JSONArray result;
    private ListView lw, lw2;
    private String getTitleName, printMovieTitle, getSimilarPrint, url;
    private int pos;
    private View view2;

    public MainActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        responseField = findViewById(R.id.track);
        nom = findViewById(R.id.track);

// Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());
// Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);
// Start the queue
        requestQueue.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        requestQueue.stop();
    }

    public void requestMovies(View view) {
        view2 = view;
        url = "https://www.myapifilms.com/imdb/idIMDB?title="+nom.getText()+"&token=82e79fc2-6847-422b-b831-3624f2768906&format=json&language=en-us&aka=0&business=0&seasons=0&seasonYear=0&technical=0&filter=2&exactFilter=0&limit=10&forceYear=0&trailers=0&movieTrivia=0&awards=0&moviePhotos=0&movieVideos=0&actors=0&biography=0&uniqueName=0&filmography=0&bornAndDead=0&starSign=0&actorActress=0&actorTrivia=0&similarMovies=1&adultSearch=0&goofs=0&keyword=0&quotes=0&fullSize=0&companyCredits=0&filmingLocations=0";
        //list = new ArrayList<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            switch(view2.getId()) {
                                case R.id.button1:
                                    list = new ArrayList<>();
                                    data = response.getJSONObject("data");
                                    result = data.getJSONArray("movies");
                                    for (int i = 0; i < result.length(); i++) {
                                        title = result.getJSONObject(i);
                                        printMovieTitle = title.get("title").toString();
                                        list.add(printMovieTitle);
                                        aa = new ArrayAdapter<String>(MainActivity.this,
                                                android.R.layout.simple_list_item_1, list);
                                        lw = findViewById(R.id.listView1);
                                        lw.setAdapter(aa);
                                    }
                                    break;

                                case R.id.button2:
                                    try {
                                        result = data.getJSONArray("movies");

                                        if (result.toString().contains(getTitleName)) {
                                            list = new ArrayList<>();
                                            similarTitle = result.getJSONObject(pos);
                                            for (int j = 0; j < result.length(); j++) {
                                                getSimilarPrint = similarTitle.getJSONArray("similarMovies").getJSONObject(j).get("name").toString();
                                                list.add(getSimilarPrint);
                                                aa = new ArrayAdapter<String>(MainActivity.this,
                                                        android.R.layout.simple_list_item_1, list);
                                                lw = findViewById(R.id.listView1);
                                                lw.setAdapter(aa);
                                            }
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    break;

                                case R.id.button3:
                                    try {
                                        listSavedMovies.add(getTitleName);
                                        movieSavedAdapter = new ArrayAdapter<String>(MainActivity.this,
                                                android.R.layout.simple_list_item_1, listSavedMovies);
                                        lw2 = findViewById(R.id.listView2);
                                        lw2.setAdapter(movieSavedAdapter);
                                    }
                                    catch (Exception e){
                                        Log.e("felet är", e.toString());
                                    }
                            }
                            lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                    getTitleName = list.get(position);
                                    pos = position;
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("EFel", error.toString());
                        // TODO: Handle error
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
}
