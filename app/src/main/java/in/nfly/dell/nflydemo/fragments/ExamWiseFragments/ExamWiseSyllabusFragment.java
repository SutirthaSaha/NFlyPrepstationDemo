package in.nfly.dell.nflydemo.fragments.ExamWiseFragments;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class ExamWiseSyllabusFragment extends Fragment {

    private String urlExam="http://nfly.in/gapi/load_rows_one";
    private WebView examWiseSyllabusText;
    private String syllabus;
    public ExamWiseSyllabusFragment() {
        // Required empty public constructor
    }
    String exam_id,exam_name;
    public static ExamWiseSyllabusFragment newInstance(String exam_id, String exam_name) {
        ExamWiseSyllabusFragment fragment=new ExamWiseSyllabusFragment();
        fragment.exam_id=exam_id;
        fragment.exam_name=exam_name;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_exam_wise_syllabus, container, false);
        examWiseSyllabusText=v.findViewById(R.id.examWiseSyllabusText);
        setValues();
        return v;
    }
    private void setValues() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlExam, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject;
                    JSONArray parentArray=new JSONArray(response);
                    jsonObject=parentArray.getJSONObject(0);
                    syllabus=jsonObject.getString("syllabus");
                    examWiseSyllabusText.loadDataWithBaseURL(null,syllabus, "text/html", "utf-8", null);

                    /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                        examWiseSyllabusText.setText(Html.fromHtml(syllabus,Html.FROM_HTML_MODE_COMPACT));
                    }
                    else{
                        examWiseSyllabusText.setText(Html.fromHtml(syllabus));
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
                params.put("key", "exam_id");
                params.put("value",exam_id);
                params.put("table", "nfly_exam");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }

}
