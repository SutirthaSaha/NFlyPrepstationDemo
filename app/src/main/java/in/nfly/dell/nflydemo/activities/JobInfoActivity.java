package in.nfly.dell.nflydemo.activities;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
import in.nfly.dell.nflydemo.adapters.KnowledgeBaseJobWiseAdapter;

public class JobInfoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView jobInfoRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressDialog progressDialog;

    private String urlJobDetails="http://nfly.in/gapi/load_all_rows";


    private ArrayList<String> titleDataSet=new ArrayList<String>(){};
    private ArrayList<String> imageDataSet=new ArrayList<String>(){};
    private ArrayList<String> numberDataSet=new ArrayList<String>(){};
    private ArrayList<String> idDataSet=new ArrayList<String>(){};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_info);
        toolbar=findViewById(R.id.jobInfoToolbar);
        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        jobInfoRecyclerView=findViewById(R.id.jobInfoRecyclerView);
        layoutManager=new LinearLayoutManager(JobInfoActivity.this);
        jobInfoRecyclerView.setLayoutManager(layoutManager);
        setValues();
    }
    private void setToolbar() {
        toolbar=findViewById(R.id.jobInfoToolbar);
        toolbar.setTitle("Job Info");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        MySingleton.release();
        jobInfoRecyclerView.setAdapter(null);
    }
    private void setValues() {
        progressDialog=new ProgressDialog(JobInfoActivity.this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlJobDetails, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        titleDataSet.add(arrayObject.getString("job_role"));
                        numberDataSet.add(Integer.toString(5));
                        imageDataSet.add("http://nfly.in/assets/images/job_role/"+arrayObject.getString("job_role_bg"));
                        idDataSet.add(arrayObject.getString("job_role_id"));
                    }
                    adapter=new KnowledgeBaseJobWiseAdapter(imageDataSet,titleDataSet,idDataSet,JobInfoActivity.this);
                    jobInfoRecyclerView.setAdapter(adapter);
                    progressDialog.cancel();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JobInfoActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
                params.put("key", "job_role_id");
                params.put("order", "ASC");
                params.put("table", "job_role");
                return params;
            }
        };
        MySingleton.getmInstance(JobInfoActivity.this).addToRequestQueue(stringRequest);
    }
}
