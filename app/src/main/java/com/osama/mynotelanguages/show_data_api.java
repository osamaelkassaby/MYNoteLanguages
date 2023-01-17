package com.osama.mynotelanguages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class show_data_api extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_show_data_api);

        String email = getIntent().getStringExtra("email");
        String token = getIntent().getStringExtra("token");

        WebView view;
        view = findViewById(R.id.webview);
        view.setWebViewClient(new WebViewClient());
        view.loadUrl("https://osamaelkassaby.com/apps/note/show/?show=&email="+email+"&token="+token);

    }
}