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
import in.nfly.dell.nflydemo.adapters.GDTopicsAdapter;

public class LearnGDTopicsActivity extends AppCompatActivity {

    private String section_id,section_name;
    private String urlGdTopics="http://nfly.in/gapi/load_rows_one";
    private Toolbar toolbar;

    private ArrayList<String> titleDataSet=new ArrayList<String>(){};
    private ArrayList<String> forLogicDataSet=new ArrayList<String>(){};
    private ArrayList<String> againstLogicDataSet=new ArrayList<String>(){};

    private RecyclerView gdTopicsRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_gdtopics);

        Intent intent=getIntent();
        section_id=intent.getStringExtra("section_id");
        section_name=intent.getStringExtra("section_name");
        Toast.makeText(this, section_id+" "+section_name, Toast.LENGTH_SHORT).show();

        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        gdTopicsRecyclerView=findViewById(R.id.gdTopicsRecyclerView);
        layoutManager=new LinearLayoutManager(LearnGDTopicsActivity.this,LinearLayoutManager.VERTICAL,false);
        gdTopicsRecyclerView.setLayoutManager(layoutManager);
        setValues();
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.gdTopicsToolbar);
        toolbar.setTitle(section_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }

    private void setValues() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlGdTopics, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        titleDataSet.add(arrayObject.getString("topic_name"));
                        forLogicDataSet.add(arrayObject.getString("for_logic"));
                        againstLogicDataSet.add(arrayObject.getString("against_logic"));
                    }
                    adapter=new GDTopicsAdapter(LearnGDTopicsActivity.this,titleDataSet,forLogicDataSet,againstLogicDataSet);
                    gdTopicsRecyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LearnGDTopicsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
                params.put("key", "section_id");
                params.put("value", section_id);
                params.put("table", "nfly_gd_topics");
                return params;
            }
        };
        MySingleton.getmInstance(LearnGDTopicsActivity.this).addToRequestQueue(stringRequest);
    }
}
