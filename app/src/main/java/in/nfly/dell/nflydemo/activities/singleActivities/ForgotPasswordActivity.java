package in.nfly.dell.nflydemo.activities.singleActivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.GMailSender;
import in.nfly.dell.nflydemo.activities.LoginActivity;
import in.nfly.dell.nflydemo.activities.MainActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private Button forgotPasswordCancelBtn, forgotPasswordSubmitBtn;
    private String user_email;
    private EditText forgotPasswordEmail;
    private String urlForget="http://nfly.in/profileapi/forgot_password";

    private Button okButton;
    private String key,body;
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
                getKey();
            }
        });
    }

    private void getKey() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,urlForget, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject=new JSONObject(response);
                    key=arrayObject.getString("key");
                    body="Password reset request.\n\nClick the link below to reset your password\n\n"+"http://nfly.in/landing/set_new_password/"+key;
                    //Toast.makeText(ForgotPasswordActivity.this, key, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(ForgotPasswordActivity.this, "A mail has been sent to your email-id to reset your password", Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(ForgotPasswordActivity.this);
                    dlgAlert.setMessage("A mail has been sent,follow the instructions to change your password.");
                    dlgAlert.setTitle("Message..");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();

                    dlgAlert.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getParent(), "Hello", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(ForgotPasswordActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                user_email=forgotPasswordEmail.getText().toString().trim();
                                GMailSender sender = new GMailSender("sutirocks@gmail.com",
                                        "jona1234");
                                sender.sendMail("Nfly Password Reset Request" , body,
                                        "sutirocks@gmail.com", user_email);
                            } catch (Exception e) {
                                Log.e("SendMail", e.getMessage(), e);
                            }
                        }
                    }).start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ForgotPasswordActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("X-Api-Key", "59671596837f42d974c7e9dcf38d17e8");
                return headers;
            }

            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<String, String>();
                params.put("key1","email");
                params.put("value1",forgotPasswordEmail.getText().toString());
                return params;
            }
        };
        MySingleton.getmInstance(ForgotPasswordActivity.this).addToRequestQueue(stringRequest);
    }
}
