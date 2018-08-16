package in.nfly.dell.nflydemo.activities.singleActivities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import in.nfly.dell.nflydemo.R;

public class PracticeTestInstructionsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String test_id,test_name,test_duration,num_ques;
    private TextView practiceTestInstructionText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_test_instructions);
        Intent intent=getIntent();
        test_id=intent.getStringExtra("test_id");
        test_name=intent.getStringExtra("test_name");
        test_duration=intent.getStringExtra("test_duration");
        num_ques=intent.getStringExtra("num_ques");

        practiceTestInstructionText=findViewById(R.id.practiceTestInstructionText);
        practiceTestInstructionText.setText("1. Marks : "+3*Integer.parseInt(num_ques)+"\n\n2. Per Question Marks : "+3+"\n\n3. Number of Questions : "+num_ques+"\n\n4. Duration : "+test_duration+" minutes "+"\n\n5. No negative marking \n\n6. Can't leave the test midway\n");
        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void setToolbar() {
        toolbar=findViewById(R.id.helpToolbar);
        toolbar.setTitle("Instructions");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }

    public void onToTestBtnClick(View view) {
        Intent intent=new Intent(PracticeTestInstructionsActivity.this, PracticeTestActivity.class);
        intent.putExtra("test_id",test_id);
        intent.putExtra("test_name",test_name);
        intent.putExtra("test_duration",test_duration);
        startActivity(intent);
    }
}
