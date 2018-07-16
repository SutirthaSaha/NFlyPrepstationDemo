package in.nfly.dell.nflydemo.activities.singleActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.User;
import in.nfly.dell.nflydemo.activities.GMailSender;
import in.nfly.dell.nflydemo.activities.LoginActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private Button forgotPasswordCancelBtn, forgotPasswordSubmitBtn;
    private String user_email;
    private EditText forgotPasswordEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgotPasswordCancelBtn=findViewById(R.id.forgotPasswordCancelBtn);
        forgotPasswordSubmitBtn=findViewById(R.id.forgotPasswordSubmitBtn);
        forgotPasswordEmail=findViewById(R.id.forgotPasswordEmail);
        forgotPasswordCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        forgotPasswordSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            user_email=forgotPasswordEmail.getText().toString().trim();
                            GMailSender sender = new GMailSender("sutirocks@gmail.com",
                                    "jona1234");
                            sender.sendMail("Hello from JavaMail", "Body from JavaMail",
                                    "sutirocks@gmail.com", user_email);
                            Toast.makeText(ForgotPasswordActivity.this, "A mail has been sent to your email-id to reset your password", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Log.e("SendMail", e.getMessage(), e);
                        }
                    }

                }).start();
                Intent intent=new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });





    }
}
