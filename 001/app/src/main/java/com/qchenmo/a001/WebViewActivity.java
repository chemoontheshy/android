package com.qchenmo.a001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.wv);

        //加载本地html
        //webView.loadUrl("file:///android_asset/hello.html");
        //加载网络html
        //允许调用javaScript
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://m.baidu.com");
    }
}
