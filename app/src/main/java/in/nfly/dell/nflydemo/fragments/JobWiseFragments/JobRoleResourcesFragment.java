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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobRoleResourcesFragment extends Fragment {

    private String job_role_id,job_role_name;
    private String urlJob="http://nfly.in/gapi/get_details_one";

    private TextView jobRoleResourcesText;

    public JobRoleResourcesFragment() {
        // Required empty public constructor
    }

    public static JobRoleResourcesFragment newInstance(String job_role_id, String job_role_name) {
        JobRoleResourcesFragment fragment=new JobRoleResourcesFragment();
        fragment.job_role_id=job_role_id;
        fragment.job_role_name=job_role_name;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_job_role_resources, container, false);
        jobRoleResourcesText=v.findViewById(R.id.jobRoleResourcesText);
        setValues();
        return v;
    }

    private void setValues() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlJob, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject=new JSONObject(response);

                    String resource=jsonObject.getString("resource");
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                        jobRoleResourcesText.setText(Html.fromHtml(resource,Html.FROM_HTML_MODE_COMPACT));
                    }
                    else{
                        jobRoleResourcesText.setText(Html.fromHtml(resource));
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
                params.put("table", "jr_learning");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }
}
