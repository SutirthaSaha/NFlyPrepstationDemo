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
import in.nfly.dell.nflydemo.adapters.CompanyPaperDetailsAdapter;
import in.nfly.dell.nflydemo.adapters.GDTopicsAdapter;

public class LearnCompanyPaperDetailsActivity extends AppCompatActivity {

    private String company_id,company_name;
    private String urlPaperDetails="http://nfly.in/gapi/load_rows_one";
    private Toolbar toolbar;

    private RecyclerView learnCompanyPaperDetailsRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private ArrayList<String> nameDataSet=new ArrayList<String>(){};
    private ArrayList<String> textDataSet=new ArrayList<String>(){};
    private ArrayList<String> idDataSet=new ArrayList<String>(){};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_company_paper_details);

        Intent intent=getIntent();
        company_id=intent.getStringExtra("company_id");
        company_name=intent.getStringExtra("company_name");

        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        learnCompanyPaperDetailsRecyclerView=findViewById(R.id.learnCompanyPaperDetailsRecyclerView);
        layoutManager=new LinearLayoutManager(LearnCompanyPaperDetailsActivity.this,LinearLayoutManager.VERTICAL,false);
        learnCompanyPaperDetailsRecyclerView.setLayoutManager(layoutManager);

        setValues();
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.learnCompanyPaperDetailsToolbar);
        toolbar.setTitle(company_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }
    private void setValues() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlPaperDetails, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        nameDataSet.add(arrayObject.getString("qp_name"));
                        textDataSet.add(arrayObject.getString("qp_text"));
                        idDataSet.add(arrayObject.getString("qp_id"));
                    }
                    adapter=new CompanyPaperDetailsAdapter(LearnCompanyPaperDetailsActivity.this,nameDataSet,textDataSet,idDataSet);
                    learnCompanyPaperDetailsRecyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LearnCompanyPaperDetailsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
                params.put("table", "company_qp");
                return params;
            }
        };
        MySingleton.getmInstance(LearnCompanyPaperDetailsActivity.this).addToRequestQueue(stringRequest);
    }
}
