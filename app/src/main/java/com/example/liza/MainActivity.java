package com.example.liza;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MainActivity extends AppCompatActivity {
    //https://greenslots.ru/WkyDrQSN
    //https://www.google.com/
    static final String URL_CURRENT = "https://greenslots.ru/WkyDrQSN";

    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new doit().execute();


    }
    public void startWeb() {
        webView = findViewById(R.id.web_view);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(URL_CURRENT);
    }
    public void startSpinner() {
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
    }


    class doit extends AsyncTask<Void, Void, Void> {
        String words;
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(URL_CURRENT).get();
                words = doc.text();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if (words.isEmpty()) {
                startSpinner();
            }
            else {
                startWeb();
            }
        }
    }
}
