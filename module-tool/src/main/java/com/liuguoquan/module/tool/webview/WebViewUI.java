package com.liuguoquan.module.tool.webview;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import butterknife.BindView;
import com.liuguoquan.commom.base.AppBaseFragment;
import com.liuguoquan.module.tool.R;
import com.liuguoquan.module.tool.R2;
import com.mdroid.lib.core.base.BasePresenter;
import com.mdroid.lib.core.base.Status;

/**
 * Description: WebView使用
 * https://jiandanxinli.github.io/2016-08-31.html
 * Created by liuguoquan on 2017/8/2 10:56.
 */

public class WebViewUI extends AppBaseFragment {

  @BindView(R2.id.webview) WebView mWebView;
  @BindView(R2.id.progress_bar) ProgressBar mProgressbar;

  String url = "https://www.hao123.com/";

  @Override protected Status getCurrentStatus() {
    return null;
  }

  @Override protected int getContentView() {
    return R.layout.module_tool_ui_webview;
  }

  @Override public BasePresenter initPresenter() {
    return null;
  }

  @Override protected String getPageTitle() {
    return "WebView";
  }

  @Override protected void initData(Bundle savedInstanceState) {

  }

  @Override protected void initView(View parent) {
    getStatusBar().setBackgroundResource(R.color.main_color_normal);
    getToolBar().setBackgroundResource(R.color.main_color_normal);
    getToolBarShadow().setVisibility(View.GONE);

    initWebView();//初始化WebView
    initWebSettings();//初始化WebSettings
    initWebViewClient();//初始化WebViewClient
    initWebChromeClient();//初始化WebChromeClient
  }

  private void initWebView() {
    mWebView.loadUrl(url);
  }

  private void initWebSettings() {
    WebSettings settings = mWebView.getSettings();
    //支持获取手势焦点
    mWebView.requestFocusFromTouch();
    //支持JS
    settings.setJavaScriptEnabled(true);
    //支持插件
    settings.setPluginState(WebSettings.PluginState.ON);
    //设置适应屏幕
    settings.setUseWideViewPort(true);
    settings.setLoadWithOverviewMode(true);
    //支持缩放
    settings.setSupportZoom(false);
    //隐藏原生的缩放控件
    settings.setDisplayZoomControls(false);
    //支持内容重新布局
    settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    settings.supportMultipleWindows();
    settings.setSupportMultipleWindows(true);
    //设置缓存模式
    settings.setDomStorageEnabled(true);
    settings.setDatabaseEnabled(true);
    settings.setCacheMode(WebSettings.LOAD_DEFAULT);
    settings.setAppCacheEnabled(true);
    settings.setAppCachePath(mWebView.getContext().getCacheDir().getAbsolutePath());

    //设置可访问文件
    settings.setAllowFileAccess(true);
    //当webview调用requestFocus时为webview设置节点
    settings.setNeedInitialFocus(true);
    //支持自动加载图片
    if (Build.VERSION.SDK_INT >= 19) {
      settings.setLoadsImagesAutomatically(true);
    } else {
      settings.setLoadsImagesAutomatically(false);
    }
    settings.setNeedInitialFocus(true);
    //设置编码格式
    settings.setDefaultTextEncodingName("UTF-8");
  }

  private void initWebViewClient() {
    mWebView.setWebViewClient(new WebViewClient() {

      //页面开始加载时
      @Override public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        mProgressbar.setVisibility(View.VISIBLE);
      }

      //页面完成加载时
      @Override public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        mProgressbar.setVisibility(View.GONE);
      }

      //是否在WebView内加载新页面
      //返回true表示你已经处理此次请求。返回false表示有webview自行处理，一般都是把此url加载出来。
      @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        view.loadUrl(request.toString());
        return false;
      }

      //网络错误时回调的方法
      @Override public void onReceivedError(WebView view, WebResourceRequest request,
          WebResourceError error) {
        super.onReceivedError(view, request, error);
        /**
         * 在这里写网络错误时的逻辑,比如显示一个错误页面
         *
         * */
      }

      @TargetApi(Build.VERSION_CODES.M) @Override
      public void onReceivedHttpError(WebView view, WebResourceRequest request,
          WebResourceResponse errorResponse) {
        super.onReceivedHttpError(view, request, errorResponse);
      }
    });
  }

  private void initWebChromeClient() {

    mWebView.setWebChromeClient(new WebChromeClient() {

      private Bitmap mDefaultVideoPoster;//默认的视频展示图

      @Override public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        getToolBar().setTitle(title);
      }

      @Override public Bitmap getDefaultVideoPoster() {
        if (mDefaultVideoPoster == null) {
          mDefaultVideoPoster =
              BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
          return mDefaultVideoPoster;
        }
        return super.getDefaultVideoPoster();
      }
    });
  }

  @Override public void onDestroy() {
    super.onDestroy();
    if (mWebView != null) {
      mWebView.destroy();
    }
  }
}
