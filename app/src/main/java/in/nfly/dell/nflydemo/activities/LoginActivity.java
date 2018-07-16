package in.nfly.dell.nflydemo.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.User;
import in.nfly.dell.nflydemo.activities.singleActivities.ForgotPasswordActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailId,passWord;
    private Button loginBtn;
    private TextView loginForgotPasswordText;
    private String userLogin="http://nfly.in/gapi/data_exists_two";
    int status;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailId=findViewById(R.id.emailId);
        passWord=findViewById(R.id.passWord);
        loginBtn=findViewById(R.id.loginBtn);
        loginForgotPasswordText=findViewById(R.id.loginForgotPasswordText);
        User user=new User(LoginActivity.this);
        if(user.getEmail()!=""){
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtra("email",user.getEmail());
            startActivity(intent);
            finish();
        }

        loginForgotPasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

    }

    public void loginBtnClick(View view) {
        final String email = emailId.getText().toString().trim();
        final String password = passWord.getText().toString().trim();

        progressDialog=new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        loginBtn.setEnabled(false);
        StringRequest stringRequest=new StringRequest(Request.Method.POST,userLogin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject=new JSONObject(response);
                    status=arrayObject.getInt("status");
                    if(status==200){
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("email",email);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Check Again", Toast.LENGTH_SHORT).show();
                        loginBtn.setEnabled(true);
                        progressDialog.cancel();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                loginBtn.setEnabled(true);
                progressDialog.cancel();
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
                params.put("value1",email);
                params.put("key2","password");
                params.put("value2",md5(password));
                params.put("table","user");
                return params;
            }
        };
        MySingleton.getmInstance(LoginActivity.this).addToRequestQueue(stringRequest);

    }

    public void onRegisterBtnClick(View view) {
        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

    private String md5(String in) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(in.getBytes());
            byte[] a = digest.digest();
            int len = a.length;
            StringBuilder sb = new StringBuilder(len << 1);
            for (int i = 0; i < len; i++) {
                sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
                sb.append(Character.forDigit(a[i] & 0x0f, 16));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
        return null;
    }
}
