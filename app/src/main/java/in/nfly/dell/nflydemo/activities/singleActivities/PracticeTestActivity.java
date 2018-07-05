package in.nfly.dell.nflydemo.activities.singleActivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.User;
import in.nfly.dell.nflydemo.activities.OnBoardRegisterActivity;
import in.nfly.dell.nflydemo.activities.RegisterActivity;
import in.nfly.dell.nflydemo.adapters.PracticePaperDetailsAdapter;

public class PracticeTestActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String test_id,test_name;
    private String user_id;
    private Integer status;
    private String urlUserGivenTest="http://nfly.in/gapi/data_exists_two";
    private String urlPrevAttemptId="http://nfly.in/gapi/select_max_on_2";
    private String urlInsertAttempt="http://nfly.in/gapi/insert";

    private String prev_attempt_id;
    private Integer previous_attempt_id,attempt_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_test);

        Intent intent=getIntent();
        test_id=intent.getStringExtra("test_id");
        test_name=intent.getStringExtra("test_name");

        User user=new User(PracticeTestActivity.this);
        user_id=user.getUser_id();

        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        checkUserGivenTest();
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.practiceTestToolbar);
        toolbar.setTitle(test_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }

    private void checkUserGivenTest(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlUserGivenTest, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(PracticeTestActivity.this, "User Has Given Test Before", Toast.LENGTH_SHORT).show();
                getPrevAttemptId();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PracticeTestActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                previous_attempt_id=0;
                attempt_id=1;
                insertAttempt();
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
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("key1", "user_id");
                params.put("value1", user_id);
                params.put("key2", "test_id");
                params.put("value2", test_id);
                params.put("table", "nfly_test_result");
                return params;
            }
        };
        MySingleton.getmInstance(PracticeTestActivity.this).addToRequestQueue(stringRequest);
    }

    private void getPrevAttemptId(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlPrevAttemptId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    Toast.makeText(PracticeTestActivity.this, response, Toast.LENGTH_SHORT).show();

                    prev_attempt_id=jsonObject.getString("previous_attempt_id");
                    previous_attempt_id=Integer.parseInt(prev_attempt_id);
                    attempt_id=previous_attempt_id+1;
                    //Toast.makeText(PracticeTestActivity.this, Integer.toString(previous_attempt_id)+"\n"+Integer.toString(current_attempt_id), Toast.LENGTH_SHORT).show();

                    insertAttempt();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PracticeTestActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("max_col", "attempt_id");
                params.put("key1", "user_id");
                params.put("value1", user_id);
                params.put("key2", "test_id");
                params.put("value2", test_id);
                params.put("table", "nfly_test_result");
                return params;
            }
        };
        MySingleton.getmInstance(PracticeTestActivity.this).addToRequestQueue(stringRequest);
    }

    private void insertAttempt() {
        Toast.makeText(this, "Am Here", Toast.LENGTH_SHORT).show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST,urlInsertAttempt, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject=new JSONObject(response);
                    status=arrayObject.getInt("status");
                    if(status==200){

                        Toast.makeText(PracticeTestActivity.this, "Insertion Successful", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(PracticeTestActivity.this, "Insertion Failed", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PracticeTestActivity.this, "Error", Toast.LENGTH_SHORT).show();
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
                params.put("insert_array[user_id]", user_id);
                params.put("insert_array[test_id]",test_id);
                params.put("insert_array[attempt_id]",Integer.toString(attempt_id));
                params.put("insert_array[state]","0");
                params.put("insert_array[score]","0");
                params.put("table","nfly_test_result");
                return params;
            }
        };
        MySingleton.getmInstance(PracticeTestActivity.this).addToRequestQueue(stringRequest);
    }


}
