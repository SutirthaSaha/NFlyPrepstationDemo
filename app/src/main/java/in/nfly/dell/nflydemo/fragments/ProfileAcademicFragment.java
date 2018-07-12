package in.nfly.dell.nflydemo.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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
import in.nfly.dell.nflydemo.User;
import in.nfly.dell.nflydemo.activities.MainActivity;
import in.nfly.dell.nflydemo.adapters.LearnTipsAdapter;
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
    private String oneRow="http://nfly.in/gapi/load_rows_one";
    private String urlUpdate="http://nfly.in/gapi/update";

    private String user_id;

    private TextView profileCollegeName,profileCourse,profileBranch,profileClassOf,profileCGPA;
    private TextView profileXIISchool,profileXIIBoard,profileXIIStream,profileXIIPassingyear,profileXIIPercentage;
    private TextView profileXSchool,profileXBoard,profileXStream,profileXPassingyear,profileXPercentage;
    private TextView profileGitHub,profilePlayStore,profileBehance,profileBlogLink;

    private ImageView workExperienceAddBtn, collegeEducationEditBtn, schoolEducationEditBtn, trainingsAddBtn,
    projectsAddBtn, achievementsAddBtn, workSampleEditBtn;
    
    private EditText editSchoolEducationXIISchoolName,editSchoolEducationXIIBoard,editSchoolEducationXIIStream,editSchoolEducationXIIPassingYear,editSchoolEducationXIIPercentage;
    private EditText editSchoolEducationXSchoolName,editSchoolEducationXBoard,editSchoolEducationXStream,editSchoolEducationXPassingYear,editSchoolEducationXPercentage;

    private EditText editCollegeEducationCollegeName,editCollegeEducationCourse,editCollegeEducationBranch,editCollegeEducationClassOf,editCollegeEducationCGPA;

    private EditText editWorkSampleGithub,editWorkSamplePlayStore,editWorkSampleBehance,editWorkSampleBlogLink;

    private ArrayList<String> DataSet=new ArrayList<String>(){
        {  add("blahblah");
            add("blah");
         }};

    private ArrayList<String> companyNameDataSet=new ArrayList<String>(){};
    private ArrayList<String> postionDataSet=new ArrayList<String>(){};
    private ArrayList<String> jobTypeDataSet=new ArrayList<String>(){};
    private ArrayList<String> startDateDataSet=new ArrayList<String>(){};
    private ArrayList<String> endDateDataSet=new ArrayList<String>(){};
    private ArrayList<String> jobDecriptionDataSet=new ArrayList<String>(){};

    private ArrayList<String> courseDataSet=new ArrayList<String>(){};
    private ArrayList<String> certDataSet=new ArrayList<String>(){};
    private ArrayList<String> durationDataSet=new ArrayList<String>(){};
    private ArrayList<String> detailsDataSet=new ArrayList<String>(){};

    private ArrayList<String> projectTitleDataSet=new ArrayList<String>(){};
    private ArrayList<String> projectDescriptionDataSet=new ArrayList<String>(){};
    private ArrayList<String> projectLinkDataSet=new ArrayList<String>(){};

    private ArrayList<String> achievementNameDataSet=new ArrayList<String>(){};
    private ArrayList<String> achievementDescriptionDataSet=new ArrayList<String>(){};

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

        workExperienceAddBtn=view.findViewById(R.id.workExperienceAddBtn);
        collegeEducationEditBtn=view.findViewById(R.id.collegeEducationEditBtn);
        schoolEducationEditBtn=view.findViewById(R.id.schoolEducationEditBtn);
        trainingsAddBtn=view.findViewById(R.id.trainingsAddBtn);
        projectsAddBtn=view.findViewById(R.id.projectsAddBtn);
        achievementsAddBtn=view.findViewById(R.id.achievementsAddBtn);
        workSampleEditBtn=view.findViewById(R.id.workSampleEditBtn);

        setWorkExperience(view);
        setTrainings(view);
        setProjects(view);
        setAchievements(view);

        setValues();
        workExperienceAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater=getLayoutInflater();
                View workExperienceAddLayout=layoutInflater.inflate(R.layout.dialog_edit_work_experience,null);

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                alertDialog.setView(workExperienceAddLayout);
                alertDialog.setCancelable(false);

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Submit clicked", Toast.LENGTH_SHORT).show();

                    }
                });
                AlertDialog alert=alertDialog.create();
                alert.show();

            }
        });

        collegeEducationEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater=getLayoutInflater();
                View collegeEducationEditLayout=layoutInflater.inflate(R.layout.dialog_edit_college_education,null);

                editCollegeEducationCollegeName=collegeEducationEditLayout.findViewById(R.id.editCollegeEducationCollegeName);
                editCollegeEducationBranch=collegeEducationEditLayout.findViewById(R.id.editCollegeEducationBranch);
                editCollegeEducationCourse=collegeEducationEditLayout.findViewById(R.id.editCollegeEducationCourse);
                editCollegeEducationClassOf=collegeEducationEditLayout.findViewById(R.id.editCollegeEducationClassOf);
                editCollegeEducationCGPA=collegeEducationEditLayout.findViewById(R.id.editCollegeEducationCgpa);

                editCollegeEducationCollegeName.setText(profileCollegeName.getText().toString().trim());
                editCollegeEducationBranch.setText(profileBranch.getText().toString().trim());
                editCollegeEducationCourse.setText(profileCourse.getText().toString().trim());
                editCollegeEducationClassOf.setText(profileClassOf.getText().toString().trim());
                editCollegeEducationCGPA.setText(profileCGPA.getText().toString().trim());

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                alertDialog.setView(collegeEducationEditLayout);
                alertDialog.setCancelable(false);

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editCollegeEducation();
                    }
                });
                AlertDialog alert=alertDialog.create();
                alert.show();

            }
        });

        schoolEducationEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater=getLayoutInflater();
                View schoolEducationEditLayout=layoutInflater.inflate(R.layout.dialog_edit_school_education,null);

                editSchoolEducationXIISchoolName=schoolEducationEditLayout.findViewById(R.id.editSchoolEducationXIISchoolName);
                editSchoolEducationXIIBoard=schoolEducationEditLayout.findViewById(R.id.editSchoolEducationXBoard);
                editSchoolEducationXIIStream=schoolEducationEditLayout.findViewById(R.id.editSchoolEducationXIIStream);
                editSchoolEducationXIIPassingYear=schoolEducationEditLayout.findViewById(R.id.editSchoolEducationXIIPassingYear);
                editSchoolEducationXIIPercentage=schoolEducationEditLayout.findViewById(R.id.editSchoolEducationXIIPercentage);

                editSchoolEducationXSchoolName=schoolEducationEditLayout.findViewById(R.id.editSchoolEducationXSchoolName);
                editSchoolEducationXBoard=schoolEducationEditLayout.findViewById(R.id.editSchoolEducationXBoard);
                editSchoolEducationXPassingYear=schoolEducationEditLayout.findViewById(R.id.editSchoolEducationXPassingYear);
                editSchoolEducationXPercentage=schoolEducationEditLayout.findViewById(R.id.editSchoolEducationXPercentage);

                editSchoolEducationXIISchoolName.setText(profileXIISchool.getText().toString());
                editSchoolEducationXIIBoard.setText(profileXIIBoard.getText().toString());
                editSchoolEducationXIIStream.setText(profileXIIStream.getText().toString());
                editSchoolEducationXIIPassingYear.setText(profileXIIPassingyear.getText().toString());
                editSchoolEducationXIIPercentage.setText(profileXIIPercentage.getText().toString());

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                alertDialog.setView(schoolEducationEditLayout);
                alertDialog.setCancelable(false);

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editSchoolEducation();

                    }
                });
                AlertDialog alert=alertDialog.create();
                alert.show();

            }
        });

        trainingsAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater=getLayoutInflater();
                View trainingsAddLayout=layoutInflater.inflate(R.layout.dialog_edit_trainings,null);

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                alertDialog.setView(trainingsAddLayout);
                alertDialog.setCancelable(false);

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Submit clicked", Toast.LENGTH_SHORT).show();

                    }
                });
                AlertDialog alert=alertDialog.create();
                alert.show();

            }
        });

        projectsAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater=getLayoutInflater();
                View projectsAddLayout=layoutInflater.inflate(R.layout.dialog_edit_projects,null);

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                alertDialog.setView(projectsAddLayout);
                alertDialog.setCancelable(false);

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Submit clicked", Toast.LENGTH_SHORT).show();

                    }
                });
                AlertDialog alert=alertDialog.create();
                alert.show();

            }
        });

        achievementsAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater=getLayoutInflater();
                View achievementsAddLayout=layoutInflater.inflate(R.layout.dialog_edit_achievements,null);

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                alertDialog.setView(achievementsAddLayout);
                alertDialog.setCancelable(false);

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Submit clicked", Toast.LENGTH_SHORT).show();

                    }
                });
                AlertDialog alert=alertDialog.create();
                alert.show();

            }
        });

        workSampleEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater=getLayoutInflater();
                View workSampleEditLayout=layoutInflater.inflate(R.layout.dialog_edit_work_sample,null);

                editWorkSampleGithub=workSampleEditLayout.findViewById(R.id.editWorkSampleGithub);
                editWorkSampleBehance=workSampleEditLayout.findViewById(R.id.editWorkSampleBehance);
                editWorkSamplePlayStore=workSampleEditLayout.findViewById(R.id.editWorkSamplePlayStore);
                editWorkSampleBlogLink=workSampleEditLayout.findViewById(R.id.editWorkSampleBlogLink);

                editWorkSampleGithub.setText(profileGitHub.getText().toString());
                editWorkSampleBehance.setText(profileBehance.getText().toString());
                editWorkSamplePlayStore.setText(profilePlayStore.getText().toString());
                editWorkSampleBlogLink.setText(profileBlogLink.getText().toString());

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                alertDialog.setView(workSampleEditLayout);
                alertDialog.setCancelable(false);

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editWorkSample();

                    }
                });
                AlertDialog alert=alertDialog.create();
                alert.show();

            }
        });
        return view;
    }

    private void editCollegeEducation() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlUpdate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                setValues();
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
                params.put("update_array[user_college]",editCollegeEducationCollegeName.getText().toString());
                params.put("update_array[user_course]",editCollegeEducationCourse.getText().toString());
                params.put("update_array[user_branch]",editCollegeEducationBranch.getText().toString());
                params.put("update_array[user_passing_year]",editCollegeEducationClassOf.getText().toString());
                params.put("update_array[user_cgpa]",editCollegeEducationCGPA.getText().toString());

                params.put("key", "user_id");
                params.put("value", user_id);
                params.put("table","user_graduation_details");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void editSchoolEducation() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlUpdate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                setValues();
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
                params.put("update_array[user_xii_school_name]",editSchoolEducationXIISchoolName.getText().toString());
                params.put("update_array[user_xii_board]",editSchoolEducationXIIBoard.getText().toString());
                params.put("update_array[user_xii_stream]",editSchoolEducationXIIStream.getText().toString());
                params.put("update_array[user_xii_marks]",editSchoolEducationXIIPercentage.getText().toString());
                params.put("update_array[user_xii_passing_year]",editSchoolEducationXIIPassingYear.getText().toString());

                params.put("update_array[user_x_school_name]",editSchoolEducationXSchoolName.getText().toString());
                params.put("update_array[user_x_board]",editSchoolEducationXBoard.getText().toString());
                params.put("update_array[user_x_marks]",editSchoolEducationXPercentage.getText().toString());
                params.put("update_array[user_x_passing_year]",editSchoolEducationXPassingYear.getText().toString());


                params.put("key", "user_id");
                params.put("value", user_id);
                params.put("table","user_school_details");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void editWorkSample() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlUpdate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                setValues();
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
                params.put("update_array[github_profile]",editWorkSampleGithub.getText().toString());
                params.put("update_array[playstore_profile]",editWorkSamplePlayStore.getText().toString());
                params.put("update_array[design_profile]",editWorkSampleBehance.getText().toString());
                params.put("update_array[blog_profile]",editWorkSampleBlogLink.getText().toString());
                params.put("key", "user_id");
                params.put("value", user_id);
                params.put("table","user_work_sample");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
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
        layoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        achievementsRecyclerView.setLayoutManager(layoutManager);

        setAchievements();
    }
    private void setAchievements() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, oneRow, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        achievementNameDataSet.add(arrayObject.getString("accolade_title"));
                        achievementDescriptionDataSet.add(arrayObject.getString("accolade_details"));
                    }
                    adapter= new ProfileAcademicAchievementsAdapter(achievementNameDataSet,achievementDescriptionDataSet);
                    achievementsRecyclerView.setAdapter(adapter);
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
                params.put("table", "user_accolades");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }
    private void setWorkExperience(View v)
    {

        workExperienceRecyclerView=v.findViewById(R.id.profileAcademicWorkExperienceRecyclerView);
        layoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        workExperienceRecyclerView.setLayoutManager(layoutManager);

        setWorkExp();


    }
    private void setWorkExp() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, oneRow, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        companyNameDataSet.add(arrayObject.getString("user_company"));
                        postionDataSet.add(arrayObject.getString("user_job_profile"));
                        jobTypeDataSet.add(arrayObject.getString("user_job_type"));
                        startDateDataSet.add(arrayObject.getString("user_job_start_date"));
                        endDateDataSet.add(arrayObject.getString("user_job_end_date"));
                        jobDecriptionDataSet.add(arrayObject.getString("user_job_desc"));
                    }

                    adapter= new ProfileAcademicWorkExperienceAdapter(companyNameDataSet,postionDataSet,jobTypeDataSet,startDateDataSet,endDateDataSet,jobDecriptionDataSet);
                    workExperienceRecyclerView.setAdapter(adapter);
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
                params.put("table", "user_employment_details");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }
    private void setTrainings(View v)
    {

        trainingsRecyclerView=v.findViewById(R.id.profileAcademicTrainingsRecyclerView);
        layoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        trainingsRecyclerView.setLayoutManager(layoutManager);

        setTrainings();

    }

    private void setTrainings() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, oneRow, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++) {
                        arrayObject = parentArray.getJSONObject(i);
                        courseDataSet.add(arrayObject.getString("user_training_course"));
                        certDataSet.add(arrayObject.getString("user_training_company"));
                        durationDataSet.add(arrayObject.getString("user_training_duration"));
                        detailsDataSet.add(arrayObject.getString("user_training_details"));
                    }
                    adapter= new ProfileAcademicTrainingsAdapter(courseDataSet,certDataSet,durationDataSet,detailsDataSet);
                    trainingsRecyclerView.setAdapter(adapter);
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
                params.put("table", "user_training_details");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }


    private void setProjects(View v)
    {
        projectsRecyclerView=v.findViewById(R.id.profileAcademicProjectsRecyclerView);
        layoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        projectsRecyclerView.setLayoutManager(layoutManager);

        setProjects();

    }

    public void setProjects(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, oneRow, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        projectTitleDataSet.add(arrayObject.getString("user_project_name"));
                        projectDescriptionDataSet.add(arrayObject.getString("user_project_details"));
                        projectLinkDataSet.add(arrayObject.getString("user_project_link"));
                    }
                    adapter= new ProfileAcademicProjectsAdapter(projectTitleDataSet,projectDescriptionDataSet,projectLinkDataSet);
                    projectsRecyclerView.setAdapter(adapter);
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
                params.put("table", "user_projects");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }

}
