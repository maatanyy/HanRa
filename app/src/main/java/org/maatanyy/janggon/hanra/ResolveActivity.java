package org.maatanyy.janggon.hanra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResolveActivity extends AppCompatActivity {
    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolve);
        ButterKnife.bind(this);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);     //웹뷰 크기 조절
        webView.getSettings().setLoadWithOverviewMode(true);

        webView.getSettings().setBuiltInZoomControls(true);  //줌 허용
        webView.getSettings().setSupportZoom(true);

        webView.setWebViewClient(new WebViewClient());
      //  webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("https://visithalla.jeju.go.kr/main/main.do");

    }
}