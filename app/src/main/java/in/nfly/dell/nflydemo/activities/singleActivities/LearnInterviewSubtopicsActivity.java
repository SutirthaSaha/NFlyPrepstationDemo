package in.nfly.dell.nflydemo.activities.singleActivities;

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
import in.nfly.dell.nflydemo.adapters.InterviewSubTopicsAdapter;

public class LearnInterviewSubtopicsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String topic_id,topic_name;

    private RecyclerView interviewSubTopicsRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private String urlInterviewSubTopics="http://nfly.in/gapi/load_rows_one";

    private ArrayList<String> titleDataSet=new ArrayList<String>(){};
    private ArrayList<String> idDataSet=new ArrayList<String>(){};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_interview_subtopics);

        Intent intent=getIntent();
        topic_id=intent.getStringExtra("topic_id");
        topic_name=intent.getStringExtra("topic_name");

        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        interviewSubTopicsRecyclerView=findViewById(R.id.interviewSubTopicsRecyclerView);
        layoutManager=new LinearLayoutManager(LearnInterviewSubtopicsActivity.this,LinearLayoutManager.VERTICAL,false);
        interviewSubTopicsRecyclerView.setLayoutManager(layoutManager);
        setValues();
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.interviewSubTopicsToolbar);
        toolbar.setTitle(topic_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }
    private void setValues() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlInterviewSubTopics, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        titleDataSet.add(arrayObject.getString("subtopic_name"));
                        idDataSet.add(arrayObject.getString("subtopic_id"));
                    }
                    adapter=new InterviewSubTopicsAdapter(LearnInterviewSubtopicsActivity.this,titleDataSet,idDataSet);
                    interviewSubTopicsRecyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LearnInterviewSubtopicsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
                params.put("key", "topic_id");
                params.put("value", topic_id);
                params.put("table", "nfly_interview_subtopics");
                return params;
            }
        };
        MySingleton.getmInstance(LearnInterviewSubtopicsActivity.this).addToRequestQueue(stringRequest);
    }
}
