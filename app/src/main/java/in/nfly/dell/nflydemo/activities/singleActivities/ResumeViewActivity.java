package in.nfly.dell.nflydemo.activities.singleActivities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import in.nfly.dell.nflydemo.R;

public class ResumeViewActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private WebView resumeViewWebView;
    private String link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_view);

        Intent intent=getIntent();
        link=intent.getStringExtra("resume_link");

        resumeViewWebView=findViewById(R.id.resumeViewWebView);
        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        resumeViewWebView.setWebChromeClient(new WebChromeClient());
        resumeViewWebView.loadUrl("http://docs.google.com/gview?embedded=true&url=" +link);
        finish();
    }
    private void setToolbar() {
        toolbar=findViewById(R.id.resumeViewToolbar);
        toolbar.setTitle("Resume View");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }
}
