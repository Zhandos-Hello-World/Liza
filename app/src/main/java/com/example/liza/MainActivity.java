package com.example.liza;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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

        webView = findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                //Users will be notified in case there's an error (i.e. no internet connection)
                Toast.makeText(MainActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).
                        show();
            }

            public void onPageFinished(WebView view, String url) {
                CookieSyncManager.getInstance().sync();
            }});
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(URL_CURRENT);
        new doit().execute();

    }
    public void startSpinner() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Ссылка не работает")
                .setMessage("Перейти на рулетку или хотите загрузить основную ссылку?")
                .setPositiveButton("Рулетка",(dialog1, which) -> {
                    Intent intent = new Intent(this, StartActivity.class);
                    startActivity(intent);
                })
                .setNegativeButton("Загрузить основную ссылку", (dialog1, which) -> {
                    webView.loadUrl("https://www.google.com/");
                });
        builder.show();
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
            if (words == null || words.isEmpty()) {
                startSpinner();

            }
        }
    }
}
