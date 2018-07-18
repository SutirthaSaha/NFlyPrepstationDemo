package in.nfly.dell.nflydemo.activities.singleActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import in.nfly.dell.nflydemo.R;

public class LearnPapersDisplayActivity extends AppCompatActivity {

    private Toolbar toolbar;
    String pdf_url="http://www.wbut.ac.in/syllabus/CSE_Final_Upto_4h_Year%20Syllabus_14.03.14.pdf";
    private String paper_id,paper_name,paper_text;
    private ProgressBar learnPapersDisplayProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_papers_display);

        Intent intent=getIntent();
        paper_id=intent.getStringExtra("paper_id");
        paper_name=intent.getStringExtra("paper_name");
        paper_text=intent.getStringExtra("paper_text");

        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        WebView webView =findViewById(R.id.learnPapersDisplayWebView);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("http://docs.google.com/gview?embedded=true&url="+"http://nfly.in/assets/images/company/"+paper_name);
        webView.setWebChromeClient(new WebChromeClient() {
            private ProgressDialog mProgress;
            @Override
            public void onProgressChanged(WebView view, int progress) {
                if (mProgress == null) {
                    mProgress = new ProgressDialog(LearnPapersDisplayActivity.this);
                    mProgress.show();
                }
                mProgress.setMessage("Loading " + String.valueOf(progress) + "%");
                if (progress == 100) {
                    mProgress.dismiss();
                    mProgress = null;
                }
            }
        });


    }

    private void setToolbar() {
        toolbar=findViewById(R.id.learnPapersDisplayToolbar);
        toolbar.setTitle(paper_text);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }
}
