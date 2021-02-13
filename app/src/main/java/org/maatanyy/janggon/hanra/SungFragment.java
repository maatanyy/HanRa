package org.maatanyy.janggon.hanra;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SungFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sung, container, false);

        WebView webView = view.findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.getSettings().setUseWideViewPort(true);     //웹뷰 크기 조절
        webView.getSettings().setLoadWithOverviewMode(true);

        webView.getSettings().setBuiltInZoomControls(true);  //줌 허용
        webView.getSettings().setSupportZoom(true);

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("http://www.jeju.go.kr/hallasan/info/info/realtime/course03.htm");

        return view;

    }

}