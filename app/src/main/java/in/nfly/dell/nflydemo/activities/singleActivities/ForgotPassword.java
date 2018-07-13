package in.nfly.dell.nflydemo.activities.singleActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.activities.LoginActivity;
import in.nfly.dell.nflydemo.activities.SplashScreenActivity;

public class ForgotPassword extends AppCompatActivity {

    Button forgotPasswordCancelBtn, forgotPasswordSubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgotPasswordCancelBtn=findViewById(R.id.forgotPasswordCancelBtn);
        forgotPasswordSubmitBtn=findViewById(R.id.forgotPasswordSubmitBtn);

        forgotPasswordCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ForgotPassword.this, LoginActivity.class);
                startActivity(intent);
            }
        });





    }
}
