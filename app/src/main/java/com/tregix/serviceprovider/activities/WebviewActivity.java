package com.tregix.serviceprovider.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tregix.serviceprovider.R;
import com.tregix.serviceprovider.Utils.Constants;

public class WebviewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);


        String url = getIntent().getStringExtra(Constants.URL);
        getSupportActionBar().setTitle(getIntent().getStringExtra(Constants.TITLE));

        WebView webView = findViewById(R.id.webview);

        webView.setWebViewClient(new MyBrowser());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(url);

    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            showProgressDialog("Loading ...");
        }
        @Override
        public void onPageFinished(WebView view, String url) {
           hideProgressDialog();
        }
    }
}
