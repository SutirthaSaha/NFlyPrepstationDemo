package in.nfly.dell.nflydemo.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.User;
import in.nfly.dell.nflydemo.adapters.CompanyTypesAdapter;
import in.nfly.dell.nflydemo.adapters.TopicWiseAdapter;
import in.nfly.dell.nflydemo.adapters.ViewPagerAdapter;
import in.nfly.dell.nflydemo.fragments.LearnGDFragment;
import in.nfly.dell.nflydemo.fragments.LearnInterviewFragment;
import in.nfly.dell.nflydemo.fragments.PracticeCompanyWiseFragment;
import in.nfly.dell.nflydemo.fragments.PracticeExamWiseFragment;
import in.nfly.dell.nflydemo.fragments.PracticeTestSeriesFragment;
import in.nfly.dell.nflydemo.fragments.PracticeTopicWiseFragment;

public class TopicWiseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayoutPractice;
    private TextView headerTitle;
    private RecyclerView topicWisePrepRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> titleDataSet=new ArrayList<String>(){{add("Quantitative Aptitude");add("Logical Reasoning");add("Verbal Ability");add("Technical");}};
    private ArrayList<String> imageDataSet=new ArrayList<String>(){{add("http://nfly.in/assets/images/app_icons/twp_quants");add("http://nfly.in/assets/images/app_icons/twp_logical");add("http://nfly.in/assets/images/app_icons/twp_verbal");add("http://nfly.in/assets/images/app_icons/twp_techincal");}};
    private ArrayList<String> idDataSet=new ArrayList<String>(){{add("1");add("2");add("3");add("4");}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_wise);

        drawerLayoutPractice=findViewById(R.id.drawerLayoutPractice);
        topicWisePrepRecyclerView=findViewById(R.id.topicWisePrepRecyclerView);
        layoutManager=new GridLayoutManager(TopicWiseActivity.this,2);
        topicWisePrepRecyclerView.setLayoutManager(layoutManager);
        setToolbar();
        setNavigationDrawer();
        setValues();
    }

    private void setValues() {
        adapter=new TopicWiseAdapter(TopicWiseActivity.this,titleDataSet,imageDataSet,idDataSet);
        topicWisePrepRecyclerView.setAdapter(adapter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        MySingleton.release();
    }

    private void setNavigationDrawer() {
        final NavigationView navigationView;
        navigationView = findViewById(R.id.navigationViewPractice);

        User user=new User(TopicWiseActivity.this);
        View header=navigationView. getHeaderView(0);
        headerTitle=header.findViewById(R.id.headerTitle);
        headerTitle.setText(user.getFname());

        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(TopicWiseActivity.this,drawerLayoutPractice,toolbar,R.string.drawer_open,R.string.drawer_close);

        drawerLayoutPractice.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(TopicWiseActivity.this,item.getTitle(),Toast.LENGTH_LONG).show();
                Intent intent;
                if (item.getTitle().equals("Home")){
                    intent=new Intent(TopicWiseActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Dashboard")){
                    intent=new Intent(TopicWiseActivity.this,DashboardActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Courses")){
                    intent=new Intent(TopicWiseActivity.this,CoursesActivity.class);
                    startActivity(intent);
                }
                if(item.getTitle().equals("Topic Wise Prep")){
                    intent=new Intent(TopicWiseActivity.this,TopicWiseActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Company Wise Prep")){
                    intent=new Intent(TopicWiseActivity.this,CompanyWisePrepActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Salary Calculator")){
                    intent=new Intent(TopicWiseActivity.this,SalaryCalculatorActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Resume Builder")) {
                    intent = new Intent(TopicWiseActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Job Info")) {
                    intent = new Intent(TopicWiseActivity.this, JobInfoActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Interview and GD Prep")) {
                    intent = new Intent(TopicWiseActivity.this, InterviewGdPrepActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Exam Wise Prep")){
                    intent=new Intent(TopicWiseActivity.this,ExamWisePrepActivity.class);
                    startActivity(intent);
                }
                drawerLayoutPractice.closeDrawer(GravityCompat.START);
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
            intent=new Intent(TopicWiseActivity.this,FeedBackActivity.class);
            startActivity(intent);
        }
        if (item.getTitle().equals("Help")){
            intent=new Intent(TopicWiseActivity.this,HelpActivity.class);
            startActivity(intent);
        }
        if(item.getTitle().equals("Sign Out")) {
            User user = new User(TopicWiseActivity.this);
            user.logOut();
            intent = new Intent(TopicWiseActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.practiceToolbar);
        toolbar.setTitle("Topic Wise Prep");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
    }

}
