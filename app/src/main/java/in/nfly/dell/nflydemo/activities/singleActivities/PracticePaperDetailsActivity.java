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
import in.nfly.dell.nflydemo.adapters.PracticePaperDetailsAdapter;

public class PracticePaperDetailsActivity extends AppCompatActivity {

    private String subtopic_id,subtopic_name;
    private Toolbar toolbar;
    private String urlPracticePapers="http://nfly.in/gapi/load_rows_one";

    private RecyclerView practicePaperDetailsRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private ArrayList<String> titleDataSet=new ArrayList<String>(){};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_paper_details);

        Intent intent=getIntent();
        subtopic_id=intent.getStringExtra("subtopic_id");
        subtopic_name=intent.getStringExtra("subtopic_name");
        Toast.makeText(this, subtopic_id, Toast.LENGTH_SHORT).show();

        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        practicePaperDetailsRecyclerView=findViewById(R.id.practicePaperDetailsRecyclerView);
        layoutManager=new LinearLayoutManager(PracticePaperDetailsActivity.this,LinearLayoutManager.VERTICAL,false);
        practicePaperDetailsRecyclerView.setLayoutManager(layoutManager);
        setValues();
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.practicePaperDetailsToolbar);
        toolbar.setTitle(subtopic_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }

    private void setValues() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlPracticePapers, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(PracticePaperDetailsActivity.this, response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        titleDataSet.add(arrayObject.getString("test_name"));
                    }
                    adapter=new PracticePaperDetailsAdapter(PracticePaperDetailsActivity.this,titleDataSet);
                    practicePaperDetailsRecyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PracticePaperDetailsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
                params.put("table", "nfly_test");
                return params;
            }
        };
        MySingleton.getmInstance(PracticePaperDetailsActivity.this).addToRequestQueue(stringRequest);
    }
}
