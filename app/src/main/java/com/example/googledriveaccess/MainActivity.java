package com.example.googledriveaccess;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity { WebView webView;@Override
protected void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);setContentView(R.layout.activity_main);
        webView=(WebView)findViewById(R.id.web);
        webView.loadUrl("https://drive.google.com/drive/folders/10AuXfgDWpncUoYnykOVKBdkGqbrEAsBF?usp=sharing");
        webView.setWebViewClient(new client()); WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true); webView.clearCache(true);
        webView.clearHistory();webView.setDownloadListener(new DownloadListener() { @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                DownloadManager.Request request=new DownloadManager.Request(Uri.parse(url));
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                DownloadManager dm=(DownloadManager)getSystemService(DOWNLOAD_SERVICE); dm.enqueue(request);
                Toast.makeText(getApplicationContext(),"Downloading Started...",Toast.LENGTH_SHORT).show(); }}); }
                private class client extends WebViewClient {
    @Override public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon); }
                @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true; }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url); }
    }
}