package in.nfly.dell.nflydemo.fragments.JobWiseFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import in.nfly.dell.nflydemo.adapters.JobRoleCareerPathAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobRoleCareerPathFragment extends Fragment {

    private String job_role_id,job_role_name;
    private String urlJob="http://nfly.in/gapi/load_rows_one";

    private RecyclerView jobRoleCareerPathRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private ArrayList<String> titleDataSet=new ArrayList<String>(){};
    private ArrayList<String> timeDataSet=new ArrayList<String>(){};
    private ArrayList<String> salaryDataSet=new ArrayList<String>(){};

    public JobRoleCareerPathFragment() {
        // Required empty public constructor
    }

    public static JobRoleCareerPathFragment newInstance(String job_role_id, String job_role_name) {
        JobRoleCareerPathFragment fragment=new JobRoleCareerPathFragment();
        fragment.job_role_id=job_role_id;
        fragment.job_role_name=job_role_name;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_job_role_career_path, container, false);
        Toast.makeText(getContext(), job_role_id+job_role_name, Toast.LENGTH_SHORT).show();
        jobRoleCareerPathRecyclerView=v.findViewById(R.id.jobRoleCareerPathRecyclerView);
        layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        jobRoleCareerPathRecyclerView.setLayoutManager(layoutManager);
        setValues();
        return v;
    }

    private void setValues() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlJob, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject;
                    JSONArray parentArray=new JSONArray(response);

                    for(int i=0;i<parentArray.length();i++){
                        jsonObject=parentArray.getJSONObject(i);
                        titleDataSet.add(jsonObject.getString("desc"));
                        salaryDataSet.add(jsonObject.getString("salary"));
                        timeDataSet.add(jsonObject.getString("duration"));
                    }

                    adapter=new JobRoleCareerPathAdapter(getContext(),titleDataSet,salaryDataSet,timeDataSet);
                    jobRoleCareerPathRecyclerView.setAdapter(adapter);
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
                params.put("table", "jr_career_track");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }

}
