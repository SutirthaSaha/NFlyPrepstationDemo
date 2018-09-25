package in.nfly.dell.nflydemo.fragments.CompanyWiseFragments;


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
public class CompanyDetailsInterviewQuesFragment extends Fragment {
    
    public String company_id, company_name;
    private String urlInterviewQuestions="http://nfly.in/gapi/load_rows_one";
    private ArrayList<String> quesDataSet=new ArrayList<String>(){};
    private ArrayList<String> ansDataSet=new ArrayList<String>(){};

    private RecyclerView companyWiseInterviewQuestionsRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private LinearLayout noCompanyInterviewQuestions;

    public CompanyDetailsInterviewQuesFragment() {
        // Required empty public constructor
    }

    public static CompanyDetailsInterviewQuesFragment  newInstance(String company_id, String company_name) {
        CompanyDetailsInterviewQuesFragment fragment = new CompanyDetailsInterviewQuesFragment();
        fragment.company_id=company_id;
        fragment.company_name=company_name;
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_company_details_interview_ques, container, false);
        companyWiseInterviewQuestionsRecyclerView=v.findViewById(R.id.companyWiseInterviewQuestionsRecyclerView);
        layoutManager=new LinearLayoutManager(getContext());
        companyWiseInterviewQuestionsRecyclerView.setLayoutManager(layoutManager);
        noCompanyInterviewQuestions=v.findViewById(R.id.noCompanyInterviewQuestions);
        setValues();
        return v;
    }

    private void setValues() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlInterviewQuestions, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    if(parentArray.length()==0){
                        noCompanyInterviewQuestions.setVisibility(View.VISIBLE);
                        companyWiseInterviewQuestionsRecyclerView.setVisibility(View.INVISIBLE);
                    }
                    else{
                        noCompanyInterviewQuestions.setVisibility(View.INVISIBLE);
                        companyWiseInterviewQuestionsRecyclerView.setVisibility(View.VISIBLE);
                    }
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        quesDataSet.add(arrayObject.getString("question"));
                        ansDataSet.add(arrayObject.getString("answer"));
                    }
                    adapter=new InterviewQuestionsAdapter(getContext(),quesDataSet,ansDataSet);
                    companyWiseInterviewQuestionsRecyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    noCompanyInterviewQuestions.setVisibility(View.VISIBLE);
                    companyWiseInterviewQuestionsRecyclerView.setVisibility(View.INVISIBLE);
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
                params.put("key", "company_id");
                params.put("value", company_id);
                params.put("table", "nfly_company_interview_questions");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }

}
