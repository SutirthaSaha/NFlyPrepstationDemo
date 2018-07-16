package in.nfly.dell.nflydemo.fragments;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import in.nfly.dell.nflydemo.adapters.KnowledgeBaseCompanyWiseAdapter;
import in.nfly.dell.nflydemo.adapters.LearnPapersAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class KnowledgeBaseCompanyWiseFragment extends Fragment {

    private RecyclerView knowBaseCompanyWiseRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressDialog progressDialog;

    private String urlPapers="http://nfly.in/gapi/load_all_rows";

    private ArrayList<String> titleDataSet=new ArrayList<String>(){};
    private ArrayList<String> imageDataSet=new ArrayList<String>(){};
    private ArrayList<String> numberDataSet=new ArrayList<String>(){};
    private ArrayList<String> idDataSet=new ArrayList<String>(){};

    public KnowledgeBaseCompanyWiseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_knowledge_base_company_wise, container, false);
        knowBaseCompanyWiseRecyclerView=v.findViewById(R.id.knowBaseCompanyWiseRecyclerView);
        layoutManager=new GridLayoutManager(getContext(),2);
        knowBaseCompanyWiseRecyclerView.setLayoutManager(layoutManager);
        setValues();
        return v;
    }

    private void setValues() {
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlPapers, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        titleDataSet.add(arrayObject.getString("company_name"));
                        numberDataSet.add(Integer.toString(5));
                        imageDataSet.add("http://nfly.in/assets/images/company/"+arrayObject.getString("company_cover"));
                        idDataSet.add(arrayObject.getString("company_id"));
                    }
                    adapter=new KnowledgeBaseCompanyWiseAdapter(imageDataSet,titleDataSet,idDataSet,getContext());
                    knowBaseCompanyWiseRecyclerView.setAdapter(adapter);
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
                params.put("order", "ASC");
                params.put("table", "company");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }


}
