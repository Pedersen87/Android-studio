package com.example.labb3;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


public class ApiService extends IntentService {

    private static final String TAG = "ApiService";

    public ApiService() {
        super(TAG);
    }

    public ApiService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        for (int i = 0; i < 5; i++) {
            Log.d(TAG, "Background service waiting for two seconds loop " + (i + 1));
            System.out.println("TJOOOOO");
            synchronized (this) {
                try {

                    wait(2000);

                } catch (Exception e) {

                    e.printStackTrace();

                }
            }
        }
    }
}
