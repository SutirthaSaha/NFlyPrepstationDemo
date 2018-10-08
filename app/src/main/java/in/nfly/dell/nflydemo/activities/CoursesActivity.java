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
import android.view.Menu;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.User;
import in.nfly.dell.nflydemo.adapters.HomeIconsAdapter;
import in.nfly.dell.nflydemo.adapters.LearnCourseAdapter;

public class CoursesActivity extends AppCompatActivity {
    private DrawerLayout drawerLayoutCourses;
    private Toolbar toolbar;
    private TextView headerTitle;
    private RecyclerView learnCourseRecyclerView, LearnCourseBannerIconsRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView learnCourseImage;
    //private ArrayList<String> titleDataSet=new ArrayList<Str   private RecyclerView.Adapter adapter;
    //    private RecyclerView.LayoutManager layoutManager;ing>(){{add("HR Questions");add("Software Tools");add("Eng. Topics");add("Company wise");add("Puzzles");add("Miscellaneous");}};
    //private ArrayList<String> imageDataSet=new ArrayList<String>(){{add(Integer.toString(R.drawable.ic_computer_black));add(Integer.toString(R.drawable.ic_computer_black));add(Integer.toString(R.drawable.ic_computer_black));add(Integer.toString(R.drawable.ic_computer_black));add(Integer.toString(R.drawable.ic_computer_black));add(Integer.toString(R.drawable.ic_computer_black));}};

    String urlCourse="http://nfly.in/gapi/load_all_rows";
    private ArrayList<String> titleDataSet=new ArrayList<String>(){};
    private ArrayList<String> subTitleDataSet=new ArrayList<String>(){};
    private ArrayList<String> imageDataSet=new ArrayList<String>(){};
    private ArrayList<String> idDataSet=new ArrayList<String>(){};


    private ArrayList<Integer> bannerImageDataSet=new ArrayList<Integer>(){
        {
            add(R.drawable.colored_video);
            add(R.drawable.hourglass);
            add(R.drawable.presentation);}};

    private ArrayList<String> bannerTitleDataSet=new ArrayList<String>(){
        {
            add("7 detailed courses");
            add("100+ hours of video");
            add("200+ videos");}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        headerTitle=findViewById(R.id.headerTitle);
        drawerLayoutCourses=findViewById(R.id.drawerLayoutCourses);
        learnCourseImage=findViewById(R.id.coursesImage);
        learnCourseRecyclerView=findViewById(R.id.coursesRecyclerView);
        layoutManager=new LinearLayoutManager(CoursesActivity.this);
        learnCourseRecyclerView.setLayoutManager(layoutManager);
        setToolbar();
        setNavigationDrawer();
        //Picasso.with(getContext()).load("https://newevolutiondesigns.com/images/freebies/google-material-design-wallpaper-2.jpg").into(learnCourseImage);
        setValues();
        setBanner();
    }
    private void setToolbar() {
        toolbar=findViewById(R.id.coursesToolbar);
        toolbar.setTitle("Courses");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
    }
    private void setNavigationDrawer() {
        final NavigationView navigationView;
        navigationView = findViewById(R.id.navigationViewCourses);

        User user=new User(CoursesActivity.this);
        View header=navigationView. getHeaderView(0);
        headerTitle=header.findViewById(R.id.headerTitle);
        headerTitle.setText(user.getFname());
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(CoursesActivity.this,drawerLayoutCourses,toolbar,R.string.drawer_open,R.string.drawer_close);

        drawerLayoutCourses.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(CoursesActivity.this,item.getTitle(),Toast.LENGTH_LONG).show();
                Intent intent;
                if (item.getTitle().equals("Home")){
                    intent=new Intent(CoursesActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Dashboard")){
                    intent=new Intent(CoursesActivity.this,DashboardActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Exam Wise Prep")){
                    intent=new Intent(CoursesActivity.this,ExamWisePrepActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Courses")){
                    intent=new Intent(CoursesActivity.this,CoursesActivity.class);
                    startActivity(intent);
                }
                if(item.getTitle().equals("Topic Wise Prep")){
                    intent=new Intent(CoursesActivity.this,TopicWiseActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Company Wise Prep")){
                    intent=new Intent(CoursesActivity.this,CompanyWisePrepActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Salary Calculator")){
                    intent=new Intent(CoursesActivity.this,SalaryCalculatorActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Resume Builder")) {
                    intent = new Intent(CoursesActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Job Info")) {
                    intent = new Intent(CoursesActivity.this, JobInfoActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Interview and GD Prep")) {
                    intent = new Intent(CoursesActivity.this, InterviewGdPrepActivity.class);
                    startActivity(intent);
                }
                drawerLayoutCourses.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        if (item.getTitle().equals("Feedback")){
            intent=new Intent(CoursesActivity.this,FeedBackActivity.class);
            startActivity(intent);
        }
        if (item.getTitle().equals("Help")){
            intent=new Intent(CoursesActivity.this,HelpActivity.class);
            startActivity(intent);
        }
        if(item.getTitle().equals("Sign Out")) {
            User user = new User(CoursesActivity.this);
            user.logOut();
            intent = new Intent(CoursesActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MySingleton.release();
        LearnCourseBannerIconsRecyclerView.setAdapter(null);
        learnCourseRecyclerView.setAdapter(null);
    }
    private void setBanner()
    {
        LearnCourseBannerIconsRecyclerView=findViewById(R.id.coursesBannerIconsRecyclerView);
        layoutManager=new GridLayoutManager(CoursesActivity.this,3);
        LearnCourseBannerIconsRecyclerView.setLayoutManager(layoutManager);

        adapter= new HomeIconsAdapter(CoursesActivity.this,bannerTitleDataSet,bannerImageDataSet);
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
                        subTitleDataSet.add("10+ videos");
                        titleDataSet.add(arrayObject.getString("nfly_course_name"));
                        imageDataSet.add(arrayObject.getString("nfly_course_bg"));
                        idDataSet.add(arrayObject.getString("nfly_course_id"));
                    }
                    adapter=new LearnCourseAdapter(imageDataSet,titleDataSet,idDataSet,subTitleDataSet,CoursesActivity.this);
                    learnCourseRecyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CoursesActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

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
        MySingleton.getmInstance(CoursesActivity.this).addToRequestQueue(stringRequest);
    }

}
