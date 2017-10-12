package com.example.saravanakumar.webyoutubevideo;

import android.app.ProgressDialog;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    LinearLayoutManager linearLayoutManager;
    String[] video = {
            "https://www.youtube.com/watch?v=qfy6Bbro8GI",
            "https://www.youtube.com/watch?v=NmSUvgGoWAY"
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialising layout manager
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView =(RecyclerView) findViewById(R.id.rv_video);

        //assigning layout manager to recycler
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        //calling adapter class
        MainAdapter mainAdapter = new MainAdapter(this,video);

        //setting the adapter to recycler view
        mRecyclerView.setAdapter(mainAdapter);
    }
}
