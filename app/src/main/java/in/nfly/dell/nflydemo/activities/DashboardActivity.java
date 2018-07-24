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
import in.nfly.dell.nflydemo.adapters.DashBoardAdapter;
import in.nfly.dell.nflydemo.adapters.LearnTipsAdapter;

public class DashboardActivity extends AppCompatActivity {

    private DrawerLayout drawerLayoutDashboard;
    private Toolbar toolbar;
    private TextView headerTitle;
    private ArrayList<String> marksDataSet=new ArrayList<String>(){};
    private ArrayList<String> titleDataSet=new ArrayList<String>(){};
    private ArrayList<String> dateDataSet=new ArrayList<String>(){};
    private ArrayList<Integer> totalMarksDataSet=new ArrayList<Integer>(){};
    private ArrayList<Integer> actualMarksDataSet=new ArrayList<Integer>(){};



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

        User user=new User(DashboardActivity.this);
        user_id=user.getUser_id();

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
                if (item.getTitle().equals("Learn")){
                    intent=new Intent(DashboardActivity.this,LearnActivity.class);
                    startActivity(intent);
                }
                if(item.getTitle().equals("Practice")){
                    intent=new Intent(DashboardActivity.this,PracticeActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Knowledge Base")){
                    intent=new Intent(DashboardActivity.this,KnowledgeBaseActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Feedback")){
                    intent=new Intent(DashboardActivity.this,FeedBackActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Salary Calculator")){
                    intent=new Intent(DashboardActivity.this,SalaryCalculatorActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Profile")){
                    intent=new Intent(DashboardActivity.this,ProfileActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Help")){
                    intent=new Intent(DashboardActivity.this,HelpActivity.class);
                    startActivity(intent);
                }
                if(item.getTitle().equals("Sign Out")){
                    User user=new User(DashboardActivity.this);
                    user.logOut();
                    intent=new Intent(DashboardActivity.this,LoginActivity.class);
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
                        dateDataSet.add(arrayObject.getString("date"));
                        marksDataSet.add(arrayObject.getString("score")+"/"+Integer.parseInt(arrayObject.getString("test_num_questions"))*3);
                        totalMarksDataSet.add(arrayObject.getInt("test_num_questions")*3);
                       //actualMarksDataSet.add(Integer.parseInt(arrayObject.getString(arrayObject.getString("score"))));

                    }
                    adapter=new DashBoardAdapter(DashboardActivity.this,titleDataSet,marksDataSet,dateDataSet,totalMarksDataSet,actualMarksDataSet);
                    dashboardRecyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
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
}
