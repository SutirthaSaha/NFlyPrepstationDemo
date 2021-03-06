package in.nfly.dell.nflydemo.fragments.CompanyWiseFragments;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyDetailsApplicationProcessFragment extends Fragment {
    
    public String company_id,company_name;
    private String urlCompany="http://nfly.in/gapi/load_rows_one";
    private TextView companyDetailsApplicationProcessText;
    private ListView companyDetailsApplicationProcessList;
    private String applicationProcess;
    private String[] steps;

    public CompanyDetailsApplicationProcessFragment() {
        // Required empty public constructor
    }
    public static CompanyDetailsApplicationProcessFragment  newInstance(String company_id, String company_name) {
        CompanyDetailsApplicationProcessFragment fragment = new CompanyDetailsApplicationProcessFragment();
        fragment.company_id=company_id;
        fragment.company_name=company_name;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_company_details_application_process, container, false);
        //companyDetailsApplicationProcessText=v.findViewById(R.id.companyDetailsApplicationProcessText);
        companyDetailsApplicationProcessList=v.findViewById(R.id.companyDetailsApplicationProcessList);
        setValues();
        return v;
    }

    private void setValues() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlCompany, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject;
                    JSONArray parentArray=new JSONArray(response);
                    jsonObject=parentArray.getJSONObject(0);
                    applicationProcess=jsonObject.getString("application_process");
                    steps=applicationProcess.split(",");
                    ArrayAdapter<String> adapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,steps);
                    companyDetailsApplicationProcessList.setAdapter(adapter);
                    /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                        companyDetailsApplicationProcessText.setText(Html.fromHtml(applicationProcess,Html.FROM_HTML_MODE_COMPACT));
                    }
                    else{
                        companyDetailsApplicationProcessText.setText(Html.fromHtml(applicationProcess));
                    }*/
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
                params.put("key", "nfly_company_id");
                params.put("value", company_id);
                params.put("table", "nfly_company");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }
}
