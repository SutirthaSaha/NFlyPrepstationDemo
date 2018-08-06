package in.nfly.dell.nflydemo.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;

import in.nfly.dell.nflydemo.R;

public class FeedBackActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private WebView feedbackWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        feedbackWebView=findViewById(R.id.feedbackWebView);
        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        feedbackWebView.setWebViewClient(new WebViewClient());
        feedbackWebView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLScuSUYmZpPDRQUEtCGvv_DYqcLYVi1dd-mBtM2jLg5VTCxeXg/viewform");
        //feedbackWebView.loadUrl("http://docs.google.com/gview?embedded=true&url=" +"http://nfly.in/profileapi/resume/12/sdf5614dflfd5");

    }
    private void setToolbar() {
        toolbar=findViewById(R.id.feedbackToolbar);
        toolbar.setTitle("Feedback");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }
}
