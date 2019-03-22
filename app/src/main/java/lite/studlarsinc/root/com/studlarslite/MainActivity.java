package lite.studlarsinc.root.com.studlarslite;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import ir.alirezabdn.wp7progress.WP10ProgressBar;


public class MainActivity extends AppCompatActivity implements NetworkChangeReceiver.ConnectionChangeCallback{

  private WebView webView;
  private WP10ProgressBar progressBar;
  private TextView textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    webView = findViewById(R.id.webView);
    progressBar = findViewById(R.id.progress);
    textView = findViewById(R.id.nointernet);
    webView.setWebViewClient(new WebViewClientDemo());
    webView.getSettings().setJavaScriptEnabled(true);
    webView.getSettings().setSupportZoom(true);       //Zoom Control on web
    webView.getSettings().setBuiltInZoomControls(true);
    IntentFilter intentFilter = new
        IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");

    NetworkChangeReceiver networkChangeReceiver = new NetworkChangeReceiver();

    registerReceiver(networkChangeReceiver, intentFilter);

    networkChangeReceiver.setConnectionChangeCallback(this);
    if(isNetworkAvailable()){
      webView.loadUrl("https://www.studlars.com");
      textView.setVisibility(View.GONE);
    }
    else textView.setVisibility(View.VISIBLE);

  }

  @Override
  public void onConnectionChange(boolean isConnected) {
    if(isConnected){
      String checker = webView.getUrl()+"";
      Log.v("CHECK", checker);
      if(checker.equals("null")||checker.equals("")){
        checker = "https://www.studlars.com";
      }
      textView.setVisibility(View.GONE);
      webView.loadUrl(checker);
    }
    else{
      textView.setVisibility(View.VISIBLE);
    }

  }




  public class WebViewClientDemo extends WebViewClient {


    @Override
    public void onPageFinished(WebView view, String url) {
      super.onPageFinished(view, url);
      progressBar.hideProgressBar();


    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
      super.onPageStarted(view, url, favicon);
      progressBar.showProgressBar();

    }




  }


  @Override

  public boolean onKeyDown(final int keyCode, final KeyEvent event) {

    if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
      webView.goBack();

      return true;
    }


    else
      return super.onKeyDown(keyCode, event);
  }

  private boolean isNetworkAvailable() {
    ConnectivityManager connectivityManager
        = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
  }



}


