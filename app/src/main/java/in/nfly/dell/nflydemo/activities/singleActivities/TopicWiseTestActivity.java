package in.nfly.dell.nflydemo.activities.singleActivities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import in.nfly.dell.nflydemo.R;

public class TopicWiseTestActivity extends AppCompatActivity {

    private String topic_id,topic_name;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_wise_test);
        Intent intent=getIntent();
        topic_id=intent.getStringExtra("topic_id");
        topic_name=intent.getStringExtra("topic_name");
        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Toast.makeText(this, topic_id, Toast.LENGTH_SHORT).show();
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.topicWiseTestToolbar);
        toolbar.setTitle(topic_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }

}
