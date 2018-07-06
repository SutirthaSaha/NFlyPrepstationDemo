package in.nfly.dell.nflydemo.activities.singleActivities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
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
import in.nfly.dell.nflydemo.adapters.InterviewQuestionsAdapter;

public class LearnInterviewQAActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String subtopic_id,subtopic_name,question_level;
    private String urlInterviewQuestions="http://nfly.in/gapi/load_rows_one";

    private RecyclerView easyInterviewQuestionsRecyclerView;
    private RecyclerView mediumInterviewQuestionsRecyclerView;
    private RecyclerView difficultInterviewQuestionsRecyclerView;

    private RecyclerView.LayoutManager easyLayoutManager,mediumLayoutManager,difficultLayoutManager;
    private RecyclerView.Adapter easyAdapter,mediumAdapter,difficultAdapter;

    private TextView easyQuestionsTextView,mediumQuestionsTextView,difficultQuestionsTextView;

    private ArrayList<String> easyQuestionsDataSet=new ArrayList<String>(){};
    private ArrayList<String> easyAnswersDataSet=new ArrayList<String>(){};
    private ArrayList<String> mediumQuestionsDataSet=new ArrayList<String>(){};
    private ArrayList<String> mediumAnswersDataSet=new ArrayList<String>(){};
    private ArrayList<String> difficultQuestionsDataSet=new ArrayList<String>(){};
    private ArrayList<String> difficultAnswersDataSet=new ArrayList<String>(){};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_interview_qa);

        Intent intent=getIntent();
        subtopic_id=intent.getStringExtra("subtopic_id");
        subtopic_name=intent.getStringExtra("subtopic_name");
        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        easyInterviewQuestionsRecyclerView=findViewById(R.id.easyInterviewQuestionsRecyclerView);
        easyLayoutManager=new LinearLayoutManager(LearnInterviewQAActivity.this,LinearLayoutManager.VERTICAL,false);
        easyInterviewQuestionsRecyclerView.setLayoutManager(easyLayoutManager);

        mediumInterviewQuestionsRecyclerView=findViewById(R.id.mediumInterviewQuestionsRecyclerView);
        mediumLayoutManager=new LinearLayoutManager(LearnInterviewQAActivity.this,LinearLayoutManager.VERTICAL,false);
        mediumInterviewQuestionsRecyclerView.setLayoutManager(mediumLayoutManager);

        difficultInterviewQuestionsRecyclerView=findViewById(R.id.difficultInterviewQuestionsRecyclerView);
        difficultLayoutManager=new LinearLayoutManager(LearnInterviewQAActivity.this,LinearLayoutManager.VERTICAL,false);
        difficultInterviewQuestionsRecyclerView.setLayoutManager(difficultLayoutManager);

        easyQuestionsTextView=findViewById(R.id.easyQuestionsTextView);
        mediumQuestionsTextView=findViewById(R.id.mediumQuestionsTextView);
        difficultQuestionsTextView=findViewById(R.id.difficultQuestionsTextView);

        setValues();
    }
    private void setToolbar() {
        toolbar=findViewById(R.id.interviewQuestionsToolbar);
        toolbar.setTitle(subtopic_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }
    private void setValues() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlInterviewQuestions, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        question_level=arrayObject.getString("question_level");
                        if(question_level.equals("Easy")){
                            easyQuestionsDataSet.add(arrayObject.getString("question_text"));
                            easyAnswersDataSet.add(arrayObject.getString("answer_text"));
                        }else if (question_level.equals("Medium")){
                            mediumQuestionsDataSet.add(arrayObject.getString("question_text"));
                            mediumAnswersDataSet.add(arrayObject.getString("answer_text"));
                        }else if (question_level.equals("Difficult")){
                            difficultQuestionsDataSet.add(arrayObject.getString("question_text"));
                            difficultAnswersDataSet.add(arrayObject.getString("answer_text"));
                        }
                    }

                    if(easyQuestionsDataSet.size()==0){
                        easyQuestionsTextView.setVisibility(View.INVISIBLE);
                    }
                    if(mediumQuestionsDataSet.size()==0){
                        mediumQuestionsTextView.setVisibility(View.INVISIBLE);
                    }
                    if(difficultQuestionsDataSet.size()==0){
                        difficultQuestionsTextView.setVisibility(View.INVISIBLE);
                    }

                    easyAdapter=new InterviewQuestionsAdapter(LearnInterviewQAActivity.this,easyQuestionsDataSet,easyAnswersDataSet);
                    easyInterviewQuestionsRecyclerView.setAdapter(easyAdapter);

                    mediumAdapter=new InterviewQuestionsAdapter(LearnInterviewQAActivity.this,mediumQuestionsDataSet,mediumAnswersDataSet);
                    mediumInterviewQuestionsRecyclerView.setAdapter(mediumAdapter);

                    difficultAdapter=new InterviewQuestionsAdapter(LearnInterviewQAActivity.this,difficultQuestionsDataSet,difficultAnswersDataSet);
                    difficultInterviewQuestionsRecyclerView.setAdapter(difficultAdapter);
                    
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LearnInterviewQAActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
                params.put("key", "subtopic_id");
                params.put("value", subtopic_id);
                params.put("table", "nfly_interview_question");
                return params;
            }
        };
        MySingleton.getmInstance(LearnInterviewQAActivity.this).addToRequestQueue(stringRequest);
    }
}
