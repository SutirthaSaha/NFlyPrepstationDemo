package in.nfly.dell.nflydemo.activities.singleActivities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;

import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.activities.DashboardActivity;
import in.nfly.dell.nflydemo.activities.TopicWiseActivity;

public class PracticeTestResultActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private int user_score,max_score;
    private TextView practiceTestResultMarks;
    private DonutProgress practiceTestResultProgress;
    private Button toPracticeTestActivity,toDashboardActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_test_result);

        practiceTestResultMarks=findViewById(R.id.practiceTestResultMarks);
        practiceTestResultProgress=findViewById(R.id.practiceTestResultProgress);
        toPracticeTestActivity=findViewById(R.id.toPracticeTestActivity);
        toDashboardActivity=findViewById(R.id.toDashboardActivity);

        Intent intent=getIntent();
        user_score=intent.getIntExtra("user_score",0);
        max_score=intent.getIntExtra("max_score",30);

        practiceTestResultMarks.setText(user_score+"/"+max_score);
        practiceTestResultProgress.setMax(max_score);
        practiceTestResultProgress.setProgress(user_score);
        setToolbar();
        toPracticeTestActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PracticeTestResultActivity.this, TopicWiseActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        toDashboardActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PracticeTestResultActivity.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.practiceTestResultToolbar);
        toolbar.setTitle("Your Score");
        toolbar.setTitleTextColor(Color.WHITE);
    }
}