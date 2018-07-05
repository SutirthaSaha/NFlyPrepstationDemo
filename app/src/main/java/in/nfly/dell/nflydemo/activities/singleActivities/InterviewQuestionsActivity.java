package in.nfly.dell.nflydemo.activities.singleActivities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import in.nfly.dell.nflydemo.R;

public class InterviewQuestionsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String subtopic_id,subtopic_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_questions);

        Intent intent=getIntent();
        subtopic_id=intent.getStringExtra("subtopic_id");
        subtopic_name=intent.getStringExtra("subtopic_name");
        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void setToolbar() {
        toolbar=findViewById(R.id.interviewQuestionsToolbar);
        toolbar.setTitle(subtopic_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }
}
