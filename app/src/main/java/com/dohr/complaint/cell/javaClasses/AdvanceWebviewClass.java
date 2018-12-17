package com.dohr.complaint.cell.javaClasses;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.dohr.complaint.cell.R;

import im.delight.android.webview.AdvancedWebView;

public class AdvanceWebviewClass extends AppCompatActivity {

    private WebView webView;
    LinearLayout backbtn;
    SwipeRefreshLayout swiperefresh;
    ProgressBar progressBar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_webview_class);
        backbtn = findViewById(R.id.backbtn);
        webView = findViewById(R.id.webview);
        progressBar1 = findViewById(R.id.progressBar1);

        if (savedInstanceState == null)
        {
            webView.loadUrl("http://www.humanrights.kp.gov.pk/");
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setPadding(0, 0, 0, 0);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            webView.getSettings().setDomStorageEnabled(true);
            webView.setWebViewClient(new MyBrowser());
        }


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdvanceWebviewClass.this, Home.class));
            }
        });

    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed() ;
        }


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
            progressBar1.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            progressBar1.setVisibility(View.GONE);
        }

    }
    // To handle "Back" key press event for WebView to go back to previous screen.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}


