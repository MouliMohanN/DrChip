package com.drchip.android.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.drchip.android.R;

public class WebViewActivity extends BaseActivity implements View.OnClickListener{

    WebView webView;
    Toolbar toolbar;
    ImageView closeWebApp;
    ProgressBar mProgressBar;
    String launchUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.web_view);
            webView = (WebView) findViewById(R.id.web_app_container);
            toolbar = (Toolbar) findViewById(R.id.standalone_web_app_toolbar);
            closeWebApp = (ImageView) toolbar.findViewById(R.id.close_web_app);
            mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
            Bundle bundle = getIntent().getExtras();
            launchUrl = bundle.getString("loadUrl");
            setSupportActionBar(toolbar);
            /*launchUrl = getIntent().getExtras().getString("app_url");
            String appName = getIntent().getExtras().getString("app_name");
            if (appName != null) {
                setTitle(appName);
            }*/
            setActionBar();
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.loadUrl(launchUrl);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {

                    setTitle(url);
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(view.getProgress());

                    super.onPageStarted(view, url, favicon);
                }

                @Override
                public void onLoadResource(WebView view, String url) {
                    try {
                        mProgressBar.setProgress(view.getProgress());
                    } catch (Exception exception) {
                    }
                    super.onLoadResource(view, url);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    mProgressBar.setVisibility(View.GONE);
                    mProgressBar.setProgress(view.getProgress());
                    super.onPageFinished(view, url);
                }

                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                    mProgressBar.setVisibility(View.GONE);
                    mProgressBar.setProgress(view.getProgress());
                    super.onReceivedError(view, request, error);
                }

                @Override
                public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                    mProgressBar.setVisibility(View.GONE);
                    mProgressBar.setProgress(view.getProgress());
                    super.onReceivedHttpError(view, request, errorResponse);
                }

                @Override
                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                    mProgressBar.setVisibility(View.GONE);
                    mProgressBar.setProgress(view.getProgress());
                    super.onReceivedSslError(view, handler, error);
                }

                @Override
                public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
                    super.onReceivedClientCertRequest(view, request);
                }

                @Override
                public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                    super.onReceivedHttpAuthRequest(view, handler, host, realm);
                }

                @Override
                public void onUnhandledInputEvent(WebView view, InputEvent event) {
                    super.onUnhandledInputEvent(view, event);
                }

                @Override
                public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
                    super.onUnhandledKeyEvent(view, event);
                }

                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    super.onReceivedError(view, errorCode, description, failingUrl);
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                    if (url.startsWith("http:") || url.startsWith("https:")) {
                        return false;
                    } else {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                }
            });
            closeWebApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        webView.loadUrl("about:blank");
        webView.removeAllViewsInLayout();
        webView.setWebViewClient(null);
        super.onDestroy();

    }

    private void setActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle("");
        }
    }

    public void setTitle(String title) {
        ((TextView)findViewById(R.id.toolbar_title)).setText(title);
    }

    @Override
    public void onBackPressed() {
        if (null != webView && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {

    }

}
