package in.nfly.dell.nflydemo.fragments.JobWiseFragments;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
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

import java.util.HashMap;
import java.util.Map;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobRoleOverviewFragment extends Fragment {

    private String job_role_id,job_role_name;
    private String urlJob="http://nfly.in/gapi/load_rows_one";
    private String job_intro,similar_jobs,nature_of_work,eligibility_criteria,skills_required,traits_required,job_outlook;
    private TextView jobRoleOverviewIntro,jobRoleOverviewSimilarJobs,jobRoleOverviewNatOfWork,jobRoleOverviewEliCri,jobRoleOverviewSkillsReq,jobRoleOverviewTraitsReq,jobRoleOverviewJobOutlook;

    public JobRoleOverviewFragment() {
        // Required empty public constructor
    }

    public static JobRoleOverviewFragment newInstance(String job_role_id, String job_role_name) {
        JobRoleOverviewFragment fragment=new JobRoleOverviewFragment();
        fragment.job_role_id=job_role_id;
        fragment.job_role_name=job_role_name;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_job_role_overview, container, false);
        jobRoleOverviewIntro=v.findViewById(R.id.jobRoleOverViewIntro);
        jobRoleOverviewSimilarJobs=v.findViewById(R.id.jobRoleOverViewSimilarJobs);
        jobRoleOverviewNatOfWork=v.findViewById(R.id.jobRoleOverViewNatOfWork);
        jobRoleOverviewEliCri=v.findViewById(R.id.jobRoleOverViewEliCri);
        jobRoleOverviewSkillsReq=v.findViewById(R.id.jobRoleOverViewSkillsReq);
        jobRoleOverviewTraitsReq=v.findViewById(R.id.jobRoleOverViewTraitsReq);
        jobRoleOverviewJobOutlook=v.findViewById(R.id.jobRoleOverViewJobOutlook);
        setValues();
        return v;
    }

    private void setValues() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlJob, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject;
                    JSONArray parentArray=new JSONArray(response);
                    jsonObject=parentArray.getJSONObject(0);

                    job_intro=jsonObject.getString("job_intro");
                    similar_jobs=jsonObject.getString("similar_jobs");
                    nature_of_work=jsonObject.getString("nature_of_work");
                    eligibility_criteria=jsonObject.getString("eligibility_criteria");
                    skills_required=jsonObject.getString("skills_required");
                    traits_required=jsonObject.getString("traits_required");
                    job_outlook=jsonObject.getString("job_outlook");

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                        jobRoleOverviewIntro.setText(Html.fromHtml(job_intro,Html.FROM_HTML_MODE_COMPACT));
                        jobRoleOverviewSimilarJobs.setText(Html.fromHtml(similar_jobs,Html.FROM_HTML_MODE_COMPACT));
                        jobRoleOverviewNatOfWork.setText(Html.fromHtml(nature_of_work,Html.FROM_HTML_MODE_COMPACT));
                        jobRoleOverviewEliCri.setText(Html.fromHtml(eligibility_criteria,Html.FROM_HTML_MODE_COMPACT));
                        jobRoleOverviewSkillsReq.setText(Html.fromHtml(skills_required,Html.FROM_HTML_MODE_COMPACT));
                        jobRoleOverviewTraitsReq.setText(Html.fromHtml(traits_required,Html.FROM_HTML_MODE_COMPACT));
                        jobRoleOverviewJobOutlook.setText(Html.fromHtml(job_outlook,Html.FROM_HTML_MODE_COMPACT));
                    }
                    else{
                        jobRoleOverviewIntro.setText(Html.fromHtml(job_intro));
                        jobRoleOverviewSimilarJobs.setText(Html.fromHtml(similar_jobs));
                        jobRoleOverviewNatOfWork.setText(Html.fromHtml(nature_of_work));
                        jobRoleOverviewEliCri.setText(Html.fromHtml(eligibility_criteria));
                        jobRoleOverviewSkillsReq.setText(Html.fromHtml(skills_required));
                        jobRoleOverviewTraitsReq.setText(Html.fromHtml(traits_required));
                        jobRoleOverviewJobOutlook.setText(Html.fromHtml(job_outlook));
                    }
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
