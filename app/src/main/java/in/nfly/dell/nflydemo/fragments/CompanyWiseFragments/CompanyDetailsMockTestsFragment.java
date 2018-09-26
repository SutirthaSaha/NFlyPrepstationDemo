package in.nfly.dell.nflydemo.fragments.CompanyWiseFragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import in.nfly.dell.nflydemo.adapters.CompanyMockTestAdapter;
import in.nfly.dell.nflydemo.adapters.PracticeCompanyWiseAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyDetailsMockTestsFragment extends Fragment {
    
    public String company_id,company_name;
    private String urlCompanyWise="http://nfly.in/gapi/load_rows_one";
    private ProgressDialog progressDialog;

    private RecyclerView companyMockTestRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private TextView companyNoTestText;

    private ArrayList<String> titleDataSet=new ArrayList<String>(){};
    private ArrayList<String> idDataSet=new ArrayList<String>(){};
    private ArrayList<String> statusDataSet=new ArrayList<String>(){};
    private ArrayList<String> dateDataSet=new ArrayList<String>(){};
    
    public CompanyDetailsMockTestsFragment() {
        // Required empty public constructor
    }

    public static CompanyDetailsMockTestsFragment  newInstance(String company_id, String company_name) {
        CompanyDetailsMockTestsFragment fragment = new CompanyDetailsMockTestsFragment();
        fragment.company_id=company_id;
        fragment.company_name=company_name;
        return fragment;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_company_details_mock_tests, container, false);
        companyMockTestRecyclerView=v.findViewById(R.id.companyMockTestRecyclerView);
        companyNoTestText=v.findViewById(R.id.companyNoTestText);
        layoutManager=new LinearLayoutManager(getContext());
        companyMockTestRecyclerView.setLayoutManager(layoutManager);
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
                        titleDataSet.add(arrayObject.getString("test_name"));
                        statusDataSet.add(arrayObject.getString("test_status"));
                        idDataSet.add(arrayObject.getString("test_id"));
                        dateDataSet.add(arrayObject.getString("test_available_from"));
                    }
                    adapter=new CompanyMockTestAdapter(getContext(),titleDataSet,statusDataSet,dateDataSet,idDataSet);
                    companyMockTestRecyclerView.setAdapter(adapter);
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
                params.put("key", "company_id");
                params.put("value",company_id );
                params.put("table", "nfly_company_test");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }

}
