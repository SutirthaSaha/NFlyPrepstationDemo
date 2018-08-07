package in.nfly.dell.nflydemo.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import in.nfly.dell.nflydemo.R;

public class HelpActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView helpPhoneNumber,helpEmailId,helpWhatsappNumber,helpLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        helpPhoneNumber=findViewById(R.id.helpPhoneNumber);
        helpEmailId=findViewById(R.id.helpEmailId);
        helpWhatsappNumber=findViewById(R.id.helpWhatsappNumber);
        helpLocation=findViewById(R.id.helpLocation);

        //22.569431,88.435776
        helpWhatsappNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsappContact(helpWhatsappNumber.getText().toString().trim());
            }
        });
        helpPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num=helpPhoneNumber.getText().toString().trim().replace(" ","");
                if(ContextCompat.checkSelfPermission(HelpActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num)));
                }
                else{
                    ActivityCompat.requestPermissions(HelpActivity.this,new String[]{Manifest.permission.CALL_PHONE},100);
                }
            }
        });
        helpLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String map = "http://maps.google.co.in/maps?q=" + "Netguru Building, 2nd Floor, E 2/4, Block - GP, Sector - 5, Salt Lake, GP Block, Sector V, Salt Lake City, Kolkata, West Bengal 700091";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                startActivity(i);
            }
        });
        helpEmailId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:support@nfly.in"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Your Query");
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });
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
    void openWhatsappContact(String number) {
        Uri uri = Uri.parse("smsto:" + number);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.setPackage("com.whatsapp");
        startActivity(i);
    }
}
