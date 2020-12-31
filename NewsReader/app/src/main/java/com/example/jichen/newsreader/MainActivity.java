package com.example.jichen.newsreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private static int numItem = 20;

    ArrayList<JSONObject> jsonObjects = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();

    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        DownloadTask task = new DownloadTask();
        try {
            JSONArray topStories = task.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty").get();

            if(topStories == null || topStories.length() == 0) {
                Toast.makeText(getApplicationContext(), "failed to get content", Toast.LENGTH_SHORT).show();
            }
            else {
                for(int i = 0; i < topStories.length(); i++) {
                    JSONObject jsonObject = topStories.getJSONObject(i);
                    titles.add(jsonObject.getString("title"));
                    urls.add(jsonObject.getString("url"));
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                intent.putExtra("url", urls.get(i));
                startActivity(intent);
            }
        });

    }

    public class DownloadTask extends AsyncTask<String, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(String... urls) {
            JSONArray topStories = new JSONArray();

            try {
                StringBuffer result = new StringBuffer();
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);

                int data = reader.read();
                while(data != -1) {
                    char current = (char) data;
                    result.append(current);
                    data = reader.read();
                }

                Log.i("URL Content", result.toString());
                JSONArray jsonArray = new JSONArray(result.toString());

                if(jsonArray.length() < numItem) {
                    numItem = jsonArray.length();
                }

                for(int i = 0; i < numItem; i++) {
                    StringBuffer articleInfo = new StringBuffer();
                    url = new URL("https://hacker-news.firebaseio.com/v0/item/" + jsonArray.getString(i) + ".json?print=pretty");
                    connection = (HttpURLConnection) url.openConnection();
                    inputStream = connection.getInputStream();
                    reader = new InputStreamReader(inputStream);
                    data = reader.read();
                    while(data != -1) {
                        char current = (char) data;
                        articleInfo.append(current);
                        data = reader.read();
                    }
                    Log.i("ArticleInfo", articleInfo.toString());
                    JSONObject jsonObject = new JSONObject(articleInfo.toString());
                    if(!jsonObject.isNull("title") && !jsonObject.isNull("url")) {
                        topStories.put(jsonObject);
                    }
                }
                return topStories;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}