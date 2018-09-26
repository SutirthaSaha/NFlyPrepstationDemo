package in.nfly.dell.nflydemo.activities.singleActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.adapters.CompanyTypeWiseAdapter;
import in.nfly.dell.nflydemo.adapters.TopicsSubTopicsAdapter;

public class TopicWiseSubTopicsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String sub_topic_id,sub_topic_name;
    private RecyclerView topicWiseSubTopicsRecylclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressDialog progressDialog;

    private String urlRowOne="http://nfly.in/gapi/load_rows_one";

    private ArrayList<String> titleDataSet=new ArrayList<String>(){};
    private ArrayList<String> imageDataSet=new ArrayList<String>(){};
    private ArrayList<String> questionsDataSet=new ArrayList<String>(){};
    private ArrayList<String> idDataSet=new ArrayList<String>(){};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_wise_sub_topics);

        Intent intent=getIntent();
        sub_topic_id=intent.getStringExtra("sub_topic_id");
        sub_topic_name=intent.getStringExtra("sub_topic_name");
        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        topicWiseSubTopicsRecylclerView=findViewById(R.id.topicWiseSubTopicsRecyclerView);
        layoutManager=new LinearLayoutManager(TopicWiseSubTopicsActivity.this);
        topicWiseSubTopicsRecylclerView.setLayoutManager(layoutManager);
        setValues();
    }
    private void setToolbar() {
        toolbar=findViewById(R.id.topicWiseSubTopicsToolbar);
        toolbar.setTitle(sub_topic_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }
    private void setValues() {
        progressDialog=new ProgressDialog(TopicWiseSubTopicsActivity.this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlRowOne, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(TopicWiseSubTopicsActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        titleDataSet.add(arrayObject.getString("topic_name"));
                        imageDataSet.add(Integer.toString(R.drawable.colored_company));
                        idDataSet.add(arrayObject.getString("topic_id"));
                        questionsDataSet.add(arrayObject.getString("num_questions")+" Questions");
                    }
                    adapter=new TopicsSubTopicsAdapter(imageDataSet,titleDataSet,idDataSet,questionsDataSet,TopicWiseSubTopicsActivity.this);
                    topicWiseSubTopicsRecylclerView.setAdapter(adapter);
                    progressDialog.cancel();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TopicWiseSubTopicsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "topic_type");
                params.put("value", sub_topic_id);
                params.put("table", "nfly_practice_topic");
                return params;
            }
        };
        MySingleton.getmInstance(TopicWiseSubTopicsActivity.this).addToRequestQueue(stringRequest);
    }
}
