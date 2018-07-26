package in.nfly.dell.nflydemo.fragments;


import android.app.ProgressDialog;
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
import in.nfly.dell.nflydemo.adapters.LearnPapersAdapter;
import in.nfly.dell.nflydemo.adapters.PracticeCompanyWiseAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PracticeCompanyWiseFragment extends Fragment {
    private RecyclerView practiceCompanyWiseRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> titleDataSet=new ArrayList<String>(){};
    private ArrayList<String> imageDataSet=new ArrayList<String>(){};
    private ArrayList<String> numberDataSet=new ArrayList<String>(){};
    private ArrayList<String> idDataSet=new ArrayList<String>(){};
    private String urlCompanyWise="http://nfly.in/gapi/load_rows_one";
    private ProgressDialog progressDialog;

    public PracticeCompanyWiseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_practice_company_wise, container, false);
        practiceCompanyWiseRecyclerView=v.findViewById(R.id.practiceCompanyWiseRecyclerView);
        layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        practiceCompanyWiseRecyclerView.setLayoutManager(layoutManager);
        setValues();
        return v;
    }

    private void setValues() {
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlCompanyWise, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        titleDataSet.add(arrayObject.getString("subtopic_name"));
                        numberDataSet.add(Integer.toString(5));
                        idDataSet.add(arrayObject.getString("subtopic_id"));
                        imageDataSet.add("http://nfly.in/assets/images/company/"+arrayObject.getString("subtopic_bg"));
                    }
                    adapter=new PracticeCompanyWiseAdapter(titleDataSet,imageDataSet,numberDataSet,idDataSet,getContext());
                    practiceCompanyWiseRecyclerView.setAdapter(adapter);
                    progressDialog.cancel();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
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
                params.put("key", "topic_id");
                params.put("value","1" );
                params.put("table", "nfly_test_subtopic");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }
}
