package in.nfly.dell.nflydemo.fragments;


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
import in.nfly.dell.nflydemo.activities.MainActivity;
import in.nfly.dell.nflydemo.adapters.HomeIconsAdapter;
import in.nfly.dell.nflydemo.adapters.LearnCourseAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class LearnCourseFragment extends Fragment {

    private RecyclerView learnCourseRecyclerView, LearnCourseBannerIconsRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    //private ArrayList<String> titleDataSet=new ArrayList<Str   private RecyclerView.Adapter adapter;
    //    private RecyclerView.LayoutManager layoutManager;ing>(){{add("HR Questions");add("Software Tools");add("Eng. Topics");add("Company wise");add("Puzzles");add("Miscellaneous");}};
    //private ArrayList<String> imageDataSet=new ArrayList<String>(){{add(Integer.toString(R.drawable.ic_computer_black));add(Integer.toString(R.drawable.ic_computer_black));add(Integer.toString(R.drawable.ic_computer_black));add(Integer.toString(R.drawable.ic_computer_black));add(Integer.toString(R.drawable.ic_computer_black));add(Integer.toString(R.drawable.ic_computer_black));}};

    String urlCourse="http://nfly.in/gapi/load_all_rows";
    private ArrayList<String> titleDataSet=new ArrayList<String>(){};
    private ArrayList<String> imageDataSet=new ArrayList<String>(){};
    private ArrayList<String> idDataSet=new ArrayList<String>(){};


    private ArrayList<Integer> bannerImageDataSet=new ArrayList<Integer>(){
        {
            add(R.drawable.colored_video);
            add(R.drawable.colored_group);
            add(R.drawable.colored_articles);}};

    private ArrayList<String> bannerTitleDataSet=new ArrayList<String>(){
        {
            add("7 detailed courses");

            add("100+ hours of video");
            add("200+ videos");}};

    public LearnCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_learn_course, container, false);
        learnCourseRecyclerView=v.findViewById(R.id.learnCourseRecyclerView);
        layoutManager=new GridLayoutManager(getContext(),2);
        learnCourseRecyclerView.setLayoutManager(layoutManager);
        setValues();
        setBanner(v);
        return v;
    }
    private void setBanner(View view)
    {
        LearnCourseBannerIconsRecyclerView=view.findViewById(R.id.learnCourseBannerIconsRecyclerView);
        layoutManager=new GridLayoutManager(getContext(),3);
        LearnCourseBannerIconsRecyclerView.setLayoutManager(layoutManager);

        adapter= new HomeIconsAdapter(bannerTitleDataSet,bannerImageDataSet);
        LearnCourseBannerIconsRecyclerView.setAdapter(adapter);

    }


    private void setValues() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlCourse, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        titleDataSet.add(arrayObject.getString("nfly_course_name"));
                        imageDataSet.add(arrayObject.getString("nfly_course_bg"));
                        idDataSet.add(arrayObject.getString("nfly_course_id"));
                    }
                    adapter=new LearnCourseAdapter(imageDataSet,titleDataSet,idDataSet,getContext());
                    learnCourseRecyclerView.setAdapter(adapter);
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
                params.put("key", "course_id");
                params.put("order", "ASC");
                params.put("table", "nfly_course");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }

}
