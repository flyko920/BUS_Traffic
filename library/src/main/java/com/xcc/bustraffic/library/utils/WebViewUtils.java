package com.xcc.bustraffic.library.utils;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by flykozhang on 2016/12/23.
 */

public class WebViewUtils {

    private static WebView webView;

    public static WebView getWebViewInstance(Activity activity, String url) {
        webView = new WebView(activity);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);// 设置支持Javascript
        webView.requestFocus();// 触摸焦点起作用
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);// 取消滚动条
        settings.setAppCacheEnabled(true);// 启动缓存
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);// 设置缓存模式
        settings.setDomStorageEnabled(true); // 开启 localStorage

        // 设置可以支持缩放
        settings.setSupportZoom(true);// 设置出现缩放工具
        settings.setBuiltInZoomControls(true);//扩大比例的缩放
        settings.setUseWideViewPort(true);

        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//自适应屏幕
        settings.setLoadWithOverviewMode(true);


        webView.setWebViewClient(new WebViewClient() {  //使用自定义的WebViewClient

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {    //覆盖shouldOverrideUrlLoading 方法  点击链接不打开Android的系统browser中响应
                view.loadUrl(url);
                return true;
            }
        });
        webView.setOnKeyListener(new View.OnKeyListener() {//点击后退按钮,让WebView后退一页(也可以覆写Activity的onKeyDown方法)
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {  //表示按返回键  时的操作
                        webView.goBack();   //后退
                        //webview.goForward();//前进
                        return true;    //已处理
                    }
                }
                return false;
            }
        });
        webView.loadUrl(url);
        return webView;

//        webView.loadUrl("http://www.google.com");// 互联网
//        webView.loadUrl("file:///android_asset/XX.html");// 本地文件，本地文件存放在：assets文件中
    }
}
