package in.nfly.dell.nflydemo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class LearnInterviewQADifficultFragment extends Fragment {

    public String subtopic_id,subtopic_name;
    private String urlInterviewQuestions="http://nfly.in/gapi/load_rows_one";
    private ArrayList<String> difficultQuestionsDataSet=new ArrayList<String>(){};
    private ArrayList<String> difficultAnswersDataSet=new ArrayList<String>(){};
    private String question_level;

    private RecyclerView difficultInterviewQuestionsRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private LinearLayout noDifficultQuestions;

    public LearnInterviewQADifficultFragment() {
        // Required empty public constructor
    }

    public static LearnInterviewQADifficultFragment  newInstance(String subtopic_id, String subtopic_name) {
        LearnInterviewQADifficultFragment fragment = new LearnInterviewQADifficultFragment();
        fragment.subtopic_id=subtopic_id;
        fragment.subtopic_name=subtopic_name;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_learn_interview_qa_difficult, container, false);
        difficultInterviewQuestionsRecyclerView=view.findViewById(R.id.difficultInterviewQuestionsRecyclerView);
        noDifficultQuestions=view.findViewById(R.id.noDifficultQuestions);
        layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        difficultInterviewQuestionsRecyclerView.setLayoutManager(layoutManager);
        setValues();
        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        difficultInterviewQuestionsRecyclerView.setAdapter(null);
    }

    private void setValues() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlInterviewQuestions, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    if(parentArray.length()==0){
                        noDifficultQuestions.setVisibility(View.VISIBLE);
                        difficultInterviewQuestionsRecyclerView.setVisibility(View.INVISIBLE);
                    }else{
                        noDifficultQuestions.setVisibility(View.INVISIBLE);
                        difficultInterviewQuestionsRecyclerView.setVisibility(View.VISIBLE);
                    }
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        question_level=arrayObject.getString("question_level");
                        if(question_level.equals("Difficult")){
                            difficultQuestionsDataSet.add(arrayObject.getString("question_text"));
                            difficultAnswersDataSet.add(arrayObject.getString("answer_text"));
                        }
                    }
                    adapter=new InterviewQuestionsAdapter(getContext(),difficultQuestionsDataSet,difficultAnswersDataSet);
                    difficultInterviewQuestionsRecyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    noDifficultQuestions.setVisibility(View.VISIBLE);
                    difficultInterviewQuestionsRecyclerView.setVisibility(View.INVISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
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
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }
}
