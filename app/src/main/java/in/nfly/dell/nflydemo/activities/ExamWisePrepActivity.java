package in.nfly.dell.nflydemo.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
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
import in.nfly.dell.nflydemo.adapters.CompanyTypeWiseAdapter;
import in.nfly.dell.nflydemo.adapters.ExamWiseAdapter;
import in.nfly.dell.nflydemo.adapters.LearnCourseAdapter;

public class ExamWisePrepActivity extends AppCompatActivity {

    private DrawerLayout drawerLayoutExamWisePrep;
    private Toolbar toolbar;
    private TextView headerTitle;
    private RecyclerView examWisePrepRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    String urlAll="http://nfly.in/gapi/load_all_rows";
    private ArrayList<String> titleDataSet=new ArrayList<String>(){};
    private ArrayList<String> imageDataSet=new ArrayList<String>(){};
    private ArrayList<String> idDataSet=new ArrayList<String>(){};
    private ArrayList<String> paperDataSet=new ArrayList<String>(){};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_wise_prep);
        headerTitle=findViewById(R.id.headerTitle);
        drawerLayoutExamWisePrep=findViewById(R.id.drawerLayoutExamWisePrep);
        examWisePrepRecyclerView=findViewById(R.id.examWisePrepRecyclerView);
        layoutManager=new LinearLayoutManager(ExamWisePrepActivity.this);
        examWisePrepRecyclerView.setLayoutManager(layoutManager);
        setToolbar();
        setNavigationDrawer();
        //Picasso.with(getContext()).load("https://newevolutiondesigns.com/images/freebies/google-material-design-wallpaper-2.jpg").into(learnCourseImage);
        setValues();
    }
    private void setToolbar() {
        toolbar=findViewById(R.id.examWisePrepToolbar);
        toolbar.setTitle("Courses");
        toolbar.setTitleTextColor(Color.WHITE);
    }
    private void setNavigationDrawer() {
        final NavigationView navigationView;
        navigationView = findViewById(R.id.navigationViewExamWisePrep);

        User user=new User(ExamWisePrepActivity.this);
        View header=navigationView. getHeaderView(0);
        headerTitle=header.findViewById(R.id.headerTitle);
        headerTitle.setText(user.getFname());
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(ExamWisePrepActivity.this,drawerLayoutExamWisePrep,toolbar,R.string.drawer_open,R.string.drawer_close);

        drawerLayoutExamWisePrep.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(ExamWisePrepActivity.this,item.getTitle(),Toast.LENGTH_LONG).show();
                Intent intent;
                if (item.getTitle().equals("Home")){
                    intent=new Intent(ExamWisePrepActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Exam Wise Prep")){
                    intent=new Intent(ExamWisePrepActivity.this,ExamWisePrepActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Dashboard")){
                    intent=new Intent(ExamWisePrepActivity.this,DashboardActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Courses")){
                    intent=new Intent(ExamWisePrepActivity.this,CoursesActivity.class);
                    startActivity(intent);
                }
                if(item.getTitle().equals("Topic Wise Prep")){
                    intent=new Intent(ExamWisePrepActivity.this,TopicWiseActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Company Wise Prep")){
                    intent=new Intent(ExamWisePrepActivity.this,CompanyWisePrepActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Salary Calculator")){
                    intent=new Intent(ExamWisePrepActivity.this,SalaryCalculatorActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Resume Builder")) {
                    intent = new Intent(ExamWisePrepActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Job Info")) {
                    intent = new Intent(ExamWisePrepActivity.this, JobInfoActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Interview and GD Prep")) {
                    intent = new Intent(ExamWisePrepActivity.this, InterviewGdPrepActivity.class);
                    startActivity(intent);
                }
                drawerLayoutExamWisePrep.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
    private void setValues() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlAll, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        titleDataSet.add(arrayObject.getString("exam_name"));
                        imageDataSet.add(Integer.toString(R.drawable.ic_computer_white));
                        paperDataSet.add("5 Tests");
                        //imageDataSet.add(arrayObject.getString("nfly_course_bg"));
                        idDataSet.add(arrayObject.getString("exam_id"));
                    }
                    adapter=new ExamWiseAdapter(imageDataSet,titleDataSet,idDataSet,paperDataSet,ExamWisePrepActivity.this);
                    examWisePrepRecyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ExamWisePrepActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

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
                params.put("order", "ASC");
                params.put("table", "nfly_exam");
                return params;
            }
        };
        MySingleton.getmInstance(ExamWisePrepActivity.this).addToRequestQueue(stringRequest);
    }

}
