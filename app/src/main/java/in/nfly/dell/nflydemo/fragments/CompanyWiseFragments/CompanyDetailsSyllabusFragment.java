package in.nfly.dell.nflydemo.fragments.CompanyWiseFragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
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

import java.util.HashMap;
import java.util.Map;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyDetailsSyllabusFragment extends Fragment {

    public String company_id,company_name;
    private String urlCompany="http://nfly.in/gapi/load_rows_one";
    private WebView companyDetailsSyllabusText;
    private String syllabus;

    public CompanyDetailsSyllabusFragment() {
        // Required empty public constructor
    }

    public static CompanyDetailsSyllabusFragment  newInstance(String company_id, String company_name) {
        CompanyDetailsSyllabusFragment fragment = new CompanyDetailsSyllabusFragment();
        fragment.company_id=company_id;
        fragment.company_name=company_name;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_company_details_syllabus, container, false);
        companyDetailsSyllabusText=v.findViewById(R.id.companyDetailsSyllabusText);
        /*
        companyDetailsSyllabusText.getSettings().setLoadWithOverviewMode(true);
        companyDetailsSyllabusText.getSettings().setUseWideViewPort(true);
        companyDetailsSyllabusText.getSettings().setBuiltInZoomControls(true);*/
        //companyDetailsSyllabusText.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
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
                    syllabus=jsonObject.getString("syllabus");
                    companyDetailsSyllabusText.loadDataWithBaseURL(null, syllabus, "text/html", "utf-8", null);
                    /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                        companyDetailsSyllabusText.setText(Html.fromHtml(syllabus,Html.FROM_HTML_MODE_COMPACT));
                    }
                    else{
                        companyDetailsSyllabusText.setText(Html.fromHtml(syllabus));
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
