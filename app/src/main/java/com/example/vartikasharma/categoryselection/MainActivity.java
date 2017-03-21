package com.example.vartikasharma.categoryselection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String URL = "https://api.myjson.com/bins/3b0u2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fetchData();
    }

    private void fetchData() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(getApplicationContext(), "Can't fetch data", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.i(LOG_TAG, "response, " + response);
                String jsonData = response.body().string();
                Log.i(LOG_TAG, "jsonData, " + jsonData);
                Log.i(LOG_TAG, jsonData);
                Gson gson = new Gson();
                if (response.isSuccessful()) {
                    JSONObject json = null;
                    JSONArray jsonArray;
                    try {
                        json = new JSONObject(jsonData);
                        Log.i(LOG_TAG, "json, " + json);
                        Log.i(LOG_TAG, "jsonObjectVariant, " + json.get("variants"));
                        JSONObject jsonVariantGroup = json.getJSONObject("variants");
                        Log.i(LOG_TAG, "jsonVariantGroup, "+ jsonVariantGroup.get("variant_groups"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        assert json != null;
                        jsonArray = json.getJSONArray("data");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i(LOG_TAG, "response is not successful");
                }
            }
        });
    }
}
