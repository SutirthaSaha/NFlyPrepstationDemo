package in.nfly.dell.nflydemo.activities.singleActivities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import in.nfly.dell.nflydemo.R;

public class GDForAgainstActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView gdForTextView,gdAgainstTextView;
    private String for_logic,against_logic,topic_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gdfor_against);

        gdForTextView=findViewById(R.id.gdForTextView);
        gdAgainstTextView=findViewById(R.id.gdAgainstTextView);

        Intent intent=getIntent();
        for_logic=intent.getStringExtra("for_logic");
        against_logic=intent.getStringExtra("against_logic");
        topic_name=intent.getStringExtra("topic_name");


        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Toast.makeText(this, for_logic, Toast.LENGTH_SHORT).show();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            gdForTextView.setText(Html.fromHtml(for_logic,Html.FROM_HTML_MODE_COMPACT));
            gdAgainstTextView.setText(Html.fromHtml(against_logic,Html.FROM_HTML_MODE_COMPACT));
        }
        else{
            gdForTextView.setText(Html.fromHtml(for_logic));
            gdAgainstTextView.setText(Html.fromHtml(against_logic));
        }
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.gdForAgainstToolbar);
        toolbar.setTitle(topic_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }

}
