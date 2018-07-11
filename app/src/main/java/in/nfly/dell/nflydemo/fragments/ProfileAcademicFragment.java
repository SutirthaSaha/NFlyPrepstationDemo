package in.nfly.dell.nflydemo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.User;
import in.nfly.dell.nflydemo.activities.MainActivity;
import in.nfly.dell.nflydemo.adapters.ProfileAcademicAchievementsAdapter;
import in.nfly.dell.nflydemo.adapters.ProfileAcademicProjectsAdapter;
import in.nfly.dell.nflydemo.adapters.ProfileAcademicTrainingsAdapter;
import in.nfly.dell.nflydemo.adapters.ProfileAcademicWorkExperienceAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileAcademicFragment extends Fragment {

    private RecyclerView workExperienceRecyclerView;
    private RecyclerView trainingsRecyclerView;
    private RecyclerView projectsRecyclerView;
    private RecyclerView achievementsRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String urlAcademic="http://nfly.in/profileapi/academic_details";
    private String user_id;

    private TextView profileCollegeName,profileCourse,profileBranch,profileClassOf,profileCGPA;
    private TextView profileXIISchool,profileXIIBoard,profileXIIStream,profileXIIPassingyear,profileXIIPercentage;
    private TextView profileXSchool,profileXBoard,profileXStream,profileXPassingyear,profileXPercentage;
    private TextView profileGitHub,profilePlayStore,profileBehance,profileBlogLink;

    private ArrayList<String> DataSet=new ArrayList<String>(){
        {  add("blahblah");
            add("blah");
         }};
    public ProfileAcademicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_profile_academic, container, false);

        User user=new User(getActivity());
        user_id=user.getUser_id();

        profileCollegeName=view.findViewById(R.id.profileCollege);
        profileCourse=view.findViewById(R.id.profileCourse);
        profileBranch=view.findViewById(R.id.profileBranch);
        profileClassOf=view.findViewById(R.id.profileClassOf);
        profileCGPA=view.findViewById(R.id.profileCGPA);

        profileXIISchool=view.findViewById(R.id.profileXIISchool);
        profileXIIBoard=view.findViewById(R.id.profileXIIBoard);
        profileXIIStream=view.findViewById(R.id.profileXIIStream);
        profileXIIPercentage=view.findViewById(R.id.profileXIIPercentage);
        profileXIIPassingyear=view.findViewById(R.id.profileXIIPassingYear);

        profileXSchool=view.findViewById(R.id.profileXSchool);
        profileXBoard=view.findViewById(R.id.profileXBoard);
        profileXPercentage=view.findViewById(R.id.profileXPercentage);
        profileXPassingyear=view.findViewById(R.id.profileXPassingYear);

        profileGitHub=view.findViewById(R.id.profileGitHub);
        profileBehance=view.findViewById(R.id.profileBehance);
        profileBlogLink=view.findViewById(R.id.profileBlogLink);
        profilePlayStore=view.findViewById(R.id.profilePlayStore);

        setWorkExperience(view);
        setTrainings(view);
        setProjects(view);
        setAchievements(view);

        setValues();

        return view;
    }

    private void setValues() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlAcademic, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONObject gradDetails=jsonObject.getJSONObject("user_graduation_details");
                    profileCollegeName.setText(gradDetails.getString("user_college"));
                    profileCourse.setText(gradDetails.getString("user_course"));
                    profileBranch.setText(gradDetails.getString("user_branch"));
                    profileClassOf.setText(gradDetails.getString("user_passing_year"));
                    profileCGPA.setText(gradDetails.getString("user_cgpa"));

                    JSONObject schoolDetails=jsonObject.getJSONObject("user_school_details");
                    profileXIISchool.setText(schoolDetails.getString("user_xii_school_name"));
                    profileXIIBoard.setText(schoolDetails.getString("user_xii_board"));
                    profileXIIStream.setText(schoolDetails.getString("user_xii_stream"));
                    profileXIIPercentage.setText(schoolDetails.getString("user_xii_marks"));
                    profileXIIPassingyear.setText(schoolDetails.getString("user_xii_passing_year"));

                    profileXSchool.setText(schoolDetails.getString("user_x_school_name"));
                    profileXBoard.setText(schoolDetails.getString("user_x_board"));
                    profileXPercentage.setText(schoolDetails.getString("user_x_marks"));
                    profileXPassingyear.setText(schoolDetails.getString("user_x_passing_year"));

                    JSONObject workSample=jsonObject.getJSONObject("user_work_sample");
                    profileGitHub.setText(workSample.getString("github_profile"));
                    profilePlayStore.setText(workSample.getString("playstore_profile"));
                    profileBehance.setText(workSample.getString("design_profile"));
                    profileBlogLink.setText(workSample.getString("blog_profile"));

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
                params.put("key", "user_id");
                params.put("value", user_id);
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void setAchievements(View v)
    {

        achievementsRecyclerView=v.findViewById(R.id.profileAcademicAchievementsRecyclerView);
        layoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        achievementsRecyclerView.setLayoutManager(layoutManager);

        adapter= new ProfileAcademicAchievementsAdapter(DataSet,DataSet);
        achievementsRecyclerView.setAdapter(adapter);
    }


    private void setWorkExperience(View v)
    {

        workExperienceRecyclerView=v.findViewById(R.id.profileAcademicWorkExperienceRecyclerView);
        layoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        workExperienceRecyclerView.setLayoutManager(layoutManager);

        adapter= new ProfileAcademicWorkExperienceAdapter(DataSet,DataSet,DataSet,DataSet,DataSet,DataSet);
        workExperienceRecyclerView.setAdapter(adapter);
    }

    private void setTrainings(View v)
    {

        trainingsRecyclerView=v.findViewById(R.id.profileAcademicTrainingsRecyclerView);
        layoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        trainingsRecyclerView.setLayoutManager(layoutManager);

        adapter= new ProfileAcademicTrainingsAdapter(DataSet,DataSet,DataSet,DataSet);
        trainingsRecyclerView.setAdapter(adapter);
    }


    private void setProjects(View v)
    {
        projectsRecyclerView=v.findViewById(R.id.profileAcademicProjectsRecyclerView);
        layoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        projectsRecyclerView.setLayoutManager(layoutManager);

        adapter= new ProfileAcademicProjectsAdapter(DataSet,DataSet,DataSet);
        projectsRecyclerView.setAdapter(adapter);

    }

}
