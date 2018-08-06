package in.nfly.dell.nflydemo.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import in.nfly.dell.nflydemo.R;

public class HelpActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView helpTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

       /* helpTextView=findViewById(R.id.helpTextView);
        helpTextView.setText("Contact with Us\n" +
                "Phone: 8420166148\n" +
                "Email-Id: support@nfly.in\n" +
                "Address\n" +
                "E 2/4, Netguru Building, Salt Lake Sector-V, Kolkata, West Bengal\n" +
                "\n");*/
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
        toolbar.setTitle("Help");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }
}
