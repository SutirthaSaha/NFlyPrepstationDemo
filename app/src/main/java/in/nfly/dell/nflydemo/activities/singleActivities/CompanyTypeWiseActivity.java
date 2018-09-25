package in.nfly.dell.nflydemo.activities.singleActivities;

import android.app.ProgressDialog;
import android.content.Intent;
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
import in.nfly.dell.nflydemo.adapters.CompanyTypeWiseAdapter;
import in.nfly.dell.nflydemo.adapters.KnowledgeBaseCompanyWiseAdapter;

public class CompanyTypeWiseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String type_id,type_name;
    private RecyclerView companyTypeWiseRecylclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressDialog progressDialog;

    private String urlRowOne="http://nfly.in/gapi/load_rows_one";

    private ArrayList<String> titleDataSet=new ArrayList<String>(){};
    private ArrayList<String> imageDataSet=new ArrayList<String>(){};
    private ArrayList<String> sectorDataSet=new ArrayList<String>(){};
    private ArrayList<String> idDataSet=new ArrayList<String>(){};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_type_wise);

        Intent intent=getIntent();
        type_id=intent.getStringExtra("type_id");
        type_name=intent.getStringExtra("type_name");

        //Toast.makeText(this, type_id, Toast.LENGTH_SHORT).show();
        companyTypeWiseRecylclerView=findViewById(R.id.companyTypeWiseRecyclerView);
        layoutManager=new LinearLayoutManager(CompanyTypeWiseActivity.this);
        companyTypeWiseRecylclerView.setLayoutManager(layoutManager);
        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setValues();
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.companyTypeWiseToolbar);
        toolbar.setTitle(type_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }

    private void setValues() {
        progressDialog=new ProgressDialog(CompanyTypeWiseActivity.this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlRowOne, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(CompanyTypeWiseActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        titleDataSet.add(arrayObject.getString("company_name"));
                        imageDataSet.add("http://nfly.in/assets/images/company_logo/"+arrayObject.getString("logo"));
                        idDataSet.add(arrayObject.getString("nfly_company_id"));
                        sectorDataSet.add(arrayObject.getString("sector"));
                    }
                    adapter=new CompanyTypeWiseAdapter(imageDataSet,titleDataSet,idDataSet,sectorDataSet,CompanyTypeWiseActivity.this);
                    companyTypeWiseRecylclerView.setAdapter(adapter);
                    progressDialog.cancel();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CompanyTypeWiseActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
                params.put("key", "company_type");
                params.put("value", type_id);
                params.put("table", "nfly_company");
                return params;
            }
        };
        MySingleton.getmInstance(CompanyTypeWiseActivity.this).addToRequestQueue(stringRequest);
    }
}
