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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.User;
import in.nfly.dell.nflydemo.adapters.DashBoardAdapter;
import in.nfly.dell.nflydemo.adapters.LearnTipsAdapter;

public class DashboardActivity extends AppCompatActivity {

    private DrawerLayout drawerLayoutDashboard;
    private Toolbar toolbar;
    private TextView dashBoardNoTestText;
    private TextView headerTitle, numberOfTestsTextView,percentageTextView;
    private ImageView dashboardImage;
    private ArrayList<String> marksDataSet=new ArrayList<String>(){};
    private ArrayList<String> titleDataSet=new ArrayList<String>(){};
    private ArrayList<String> dateDataSet=new ArrayList<String>(){};
    private ArrayList<Integer> totalMarksDataSet=new ArrayList<Integer>(){};
    private ArrayList<Integer> actualMarksDataSet=new ArrayList<Integer>(){};

    private int numberOfTests=0;
    private float percentage=0,totalFullMarks=0,totalRecievedMarks=0;



    private RecyclerView dashboardRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private String urlDashBoard="http://nfly.in/testapi/test_result";
    private  String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        drawerLayoutDashboard=findViewById(R.id.drawerLayoutDashboard);
        setToolbar();
        setNavigationDrawer();
        dashboardRecyclerView=findViewById(R.id.dashboardRecyclerView);
        dashBoardNoTestText=findViewById(R.id.dashboardNoTestText);

        User user=new User(DashboardActivity.this);
        user_id=user.getUser_id();
        dashboardImage=findViewById(R.id.dashboardImage);
        Picasso.with(this).load("http://www.vactualpapers.com/web/wallpapers/material-design-hd-wallpaper-no-0769/1920x1920.png").into(dashboardImage);
        numberOfTestsTextView=findViewById(R.id.numberOfTests);
        percentageTextView=findViewById(R.id.percentage);
        layoutManager=new LinearLayoutManager(DashboardActivity.this, LinearLayoutManager.VERTICAL,false);
        dashboardRecyclerView.setLayoutManager(layoutManager);
        setValues();
    }
    private void setNavigationDrawer() {
        final NavigationView navigationView;
        navigationView = findViewById(R.id.navigationViewDashboard);

        User user=new User(DashboardActivity.this);
        View header=navigationView. getHeaderView(0);
        headerTitle=header.findViewById(R.id.headerTitle);
        headerTitle.setText(user.getFname());
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(DashboardActivity.this,drawerLayoutDashboard,toolbar,R.string.drawer_open,R.string.drawer_close);

        drawerLayoutDashboard.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(DashboardActivity.this,item.getTitle(),Toast.LENGTH_LONG).show();
                Intent intent;
                if (item.getTitle().equals("Home")){
                    intent=new Intent(DashboardActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Dashboard")){
                    intent=new Intent(DashboardActivity.this,DashboardActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Courses")){
                    intent=new Intent(DashboardActivity.this,CoursesActivity.class);
                    startActivity(intent);
                }
                if(item.getTitle().equals("Topic Wise Prep")){
                    intent=new Intent(DashboardActivity.this,PracticeActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Company Wise Prep")){
                    intent=new Intent(DashboardActivity.this,CompanyWisePrepActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Salary Calculator")){
                    intent=new Intent(DashboardActivity.this,SalaryCalculatorActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Resume Builder")) {
                    intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Job Info")) {
                    intent = new Intent(DashboardActivity.this, JobInfoActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Interview and GD Prep")) {
                    intent = new Intent(DashboardActivity.this, InterviewGdPrepActivity.class);
                    startActivity(intent);
                }
                drawerLayoutDashboard.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.dashboardToolbar);
        toolbar.setTitle("Dashboard");
        toolbar.setTitleTextColor(Color.WHITE);
    }
    private void setValues() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlDashBoard, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);

                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        titleDataSet.add(arrayObject.getString("test_name"));
                        //dateDataSet.add(arrayObject.getString("date"));
                        //String s="2018-09-20";

                        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                        try {
                            // Convert String to Date in java
                            Date today = sdf.parse(arrayObject.getString("date"));
                            DateFormat sdf1 = new SimpleDateFormat("dd MMM yy", Locale.UK);
                            //textView.setText(sdf1.format(today));
                            dateDataSet.add(sdf1.format(today));

                        } catch (ParseException e) {
                            e.printStackTrace();
                            dashBoardNoTestText.setVisibility(View.VISIBLE);
                        }

                        marksDataSet.add(arrayObject.getString("score")+"/"+Integer.parseInt(arrayObject.getString("test_num_questions"))*3);
                        totalMarksDataSet.add(arrayObject.getInt("test_num_questions")*3);
                        actualMarksDataSet.add(Integer.parseInt(arrayObject.getString("score")));
                        numberOfTests=numberOfTests+1;
                        totalFullMarks=totalFullMarks+(arrayObject.getInt("test_num_questions")*3);
                        totalRecievedMarks=totalRecievedMarks+(Integer.parseInt(arrayObject.getString("score")));

                    }
                    percentage=totalRecievedMarks/totalFullMarks*100;
                    numberOfTestsTextView.setText(String.valueOf(numberOfTests));
                    percentageTextView.setText(String.valueOf(String.format("%.02f",percentage)+"%"));
                    adapter=new DashBoardAdapter(DashboardActivity.this,titleDataSet,marksDataSet,dateDataSet,totalMarksDataSet,actualMarksDataSet);
                    dashboardRecyclerView.setAdapter(adapter);
                    adapter=new DashBoardAdapter(DashboardActivity.this,titleDataSet,marksDataSet,dateDataSet,totalMarksDataSet,actualMarksDataSet);
                    dashboardRecyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    dashBoardNoTestText.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DashboardActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

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
        MySingleton.getmInstance(DashboardActivity.this).addToRequestQueue(stringRequest);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MySingleton.release();
        dashboardRecyclerView.setAdapter(null);
    }
}
