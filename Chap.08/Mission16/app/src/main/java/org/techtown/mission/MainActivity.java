package org.techtown.mission;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
WebView webView;
Animation openPager;
Animation closePager;
Animation openViewer;
Animation closeViewer;
LinearLayout pager;
LinearLayout viewer;
Boolean isViewerOpen=false;
Boolean isPagerOpen=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager=findViewById(R.id.pager);
        viewer=findViewById(R.id.viewer);
        EditText ed1=findViewById(R.id.editTextTextPersonName);
        webView=findViewById(R.id.webView);
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new ViewClient());

        openPager=AnimationUtils.loadAnimation(this,R.anim.trans_open_pager);
        closePager=AnimationUtils.loadAnimation(this,R.anim.trans_close_pager);
        openViewer=AnimationUtils.loadAnimation(this,R.anim.trans_open_viewer);
        closeViewer=AnimationUtils.loadAnimation(this,R.anim.trans_close_viewer);
        PagerListener PagerListener=new PagerListener();
        ViewerListener ViewerListener=new ViewerListener();
        openPager.setAnimationListener(PagerListener);
        closePager.setAnimationListener(PagerListener);
        openViewer.setAnimationListener(ViewerListener);
        closeViewer.setAnimationListener(ViewerListener);

        Button button1=findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ed1.getText().toString().startsWith("http://")){
                    String txt=ed1.getText().toString();
                    ed1.setText("http://"+txt);
                }
                webView.loadUrl(ed1.getText().toString());
                pager.startAnimation(closePager);
                viewer.startAnimation(openViewer);
                if(getCurrentFocus()!=null){
                    InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
                }
            }
        });

        Button button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.startAnimation(openPager);
                viewer.startAnimation(closeViewer);
                webView.destroy();
            }
        });
    }

    private class ViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private class PagerListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {
            if(!isPagerOpen){
                pager.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(!isPagerOpen){
                isPagerOpen=true;
            }
            else{
                pager.setVisibility(View.INVISIBLE);
                isPagerOpen=false;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    private class ViewerListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {
            if(!isViewerOpen){
                viewer.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isViewerOpen){
                viewer.setVisibility(View.INVISIBLE);
                isViewerOpen=false;
            }
            else{
                isViewerOpen=true;
            }

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}