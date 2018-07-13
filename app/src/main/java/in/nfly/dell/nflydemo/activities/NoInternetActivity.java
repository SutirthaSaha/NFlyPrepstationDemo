package in.nfly.dell.nflydemo.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import in.nfly.dell.nflydemo.R;

public class NoInternetActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
        setToolbar();
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.noInternetToolbar);
        toolbar.setTitle("Nfly");
        toolbar.setTitleTextColor(Color.WHITE);
    }
}
