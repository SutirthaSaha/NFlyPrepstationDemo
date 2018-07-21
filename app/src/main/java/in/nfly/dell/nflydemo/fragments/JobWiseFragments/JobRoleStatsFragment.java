package in.nfly.dell.nflydemo.fragments.JobWiseFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import in.nfly.dell.nflydemo.activities.MainActivity;
import in.nfly.dell.nflydemo.adapters.JobRoleStatsAdapter;


public class JobRoleStatsFragment extends Fragment {

    private String job_role_id,job_role_name;
    private String urlJob="http://nfly.in/gapi/load_rows_one";
    private TextView jobRoleStatsJobMarketText, jobRoleStatsSalaryScoreText,jobRoleStatsWorkLifeText,jobRoleStatsFutureScoreText;

    private String salary_score,job_market_score,future_score,work_life_bal_score;
    private RecyclerView jobRoleStatsRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Integer> jobRoleStatsImageDataSet=new ArrayList<Integer>(){
        {
            add(R.drawable.colorvideo);
            add(R.drawable.coloredtest);
            add(R.drawable.colored_company);
            add(R.drawable.colored_placementpapers);
            add(R.drawable.colored_prep);
            add(R.drawable.colored_placementpapers);
            add(R.drawable.colored_prep);
            add(R.drawable.colorresume);}};
    private ArrayList<String> jobRoleStatsTitleDataSet=new ArrayList<String>(){
        {add("Median Salary");
            add("Unemployment");
            add("Jobs");
            add("Job Market");
            add("Future Growth");
            add("Work-life Balance");
            add("Stress at Work");
            add("Flexibility");
        }};



    public JobRoleStatsFragment() {
    }

    public static JobRoleStatsFragment newInstance(String job_role_id, String job_role_name) {
        JobRoleStatsFragment fragment=new JobRoleStatsFragment();
        fragment.job_role_id=job_role_id;
        fragment.job_role_name=job_role_name;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_job_role_stats, container, false);
        jobRoleStatsJobMarketText=v.findViewById(R.id.jobRoleStatsJobMarketText);
        jobRoleStatsSalaryScoreText=v.findViewById(R.id.jobRoleStatsSalaryScoreText);
        jobRoleStatsWorkLifeText=v.findViewById(R.id.jobRoleStatsWorkLifeText);
        jobRoleStatsFutureScoreText=v.findViewById(R.id.jobRoleStatsFutureScoreText);



        setCards(v);

        setValues();

        return v;
    }
    private void setCards(View v)
    {
        jobRoleStatsRecyclerView= v.findViewById(R.id.jobRoleStatsRecyclerView);
        layoutManager=new GridLayoutManager(getContext(),4);
        jobRoleStatsRecyclerView.setLayoutManager(layoutManager);

        adapter= new JobRoleStatsAdapter(getActivity(),jobRoleStatsTitleDataSet,jobRoleStatsImageDataSet);
        jobRoleStatsRecyclerView.setAdapter(adapter);

    }

    private void setValues() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlJob, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(), "Stats"+response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject;
                    JSONArray parentArray=new JSONArray(response);
                    jsonObject=parentArray.getJSONObject(0);

                    salary_score=jsonObject.getString("salary_score");
                    job_market_score=jsonObject.getString("job_market_score");
                    future_score=jsonObject.getString("future_score");
                    work_life_bal_score=jsonObject.getString("work_life_bal_score");

                    String value=job_market_score;
                    /*
                    String value="\n\nSalary Score: "+jsonObject.getString("salary_score")
                            +"\n\nJob Market Score: "+jsonObject.getString("job_market_score")
                            +"\n\nFuture Score: "+jsonObject.getString("future_score")+
                            "\n\nWork Life Balance: "+jsonObject.getString("work_life_bal_score");
                    */
                    jobRoleStatsJobMarketText.setText(job_market_score+"/10");
                    jobRoleStatsFutureScoreText.setText(future_score+"/10");
                    jobRoleStatsSalaryScoreText.setText(salary_score+"/10");
                    jobRoleStatsWorkLifeText.setText(work_life_bal_score+"/10");

                } catch (JSONException e) {
                    e.printStackTrace();
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
                params.put("key", "job_role_id");
                params.put("value", job_role_id);
                params.put("table", "job_role");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }
}
