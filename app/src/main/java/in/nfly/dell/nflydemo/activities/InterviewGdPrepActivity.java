package in.nfly.dell.nflydemo.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.User;
import in.nfly.dell.nflydemo.adapters.ViewPagerAdapter;
import in.nfly.dell.nflydemo.fragments.LearnGDFragment;
import in.nfly.dell.nflydemo.fragments.LearnInterviewFragment;

public class InterviewGdPrepActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayoutInterviewGdPrep;
    private TextView headerTitle;
    private TabLayout tabLayoutInterviewGdPrep;
    private ViewPager viewPagerInterviewGdPrep;
    private ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_gd_prep);

        drawerLayoutInterviewGdPrep=findViewById(R.id.drawerLayoutInterviewGdPrep);

        tabLayoutInterviewGdPrep=findViewById(R.id.tabLayoutInterviewGdPrep);
        viewPagerInterviewGdPrep=findViewById(R.id.viewPagerInterviewGdPrep);

        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragments(new LearnGDFragment(),"GD");
        viewPagerAdapter.addFragments(new LearnInterviewFragment(),"Interview");

        viewPagerInterviewGdPrep.setAdapter(viewPagerAdapter);
        tabLayoutInterviewGdPrep.setupWithViewPager(viewPagerInterviewGdPrep);

        Intent intent=getIntent();
        viewPagerInterviewGdPrep.setCurrentItem(intent.getIntExtra("fragment_no",0));

        setToolbar();
        setNavigationDrawer();
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.interviewGdPrepToolbar);
        toolbar.setTitle("Interview and GD Prep");
        toolbar.setTitleTextColor(Color.WHITE);
    }
    private void setNavigationDrawer() {
        final NavigationView navigationView;
        navigationView = findViewById(R.id.navigationViewInterviewGdPrep);

        User user=new User(InterviewGdPrepActivity.this);
        View header=navigationView. getHeaderView(0);
        headerTitle=header.findViewById(R.id.headerTitle);
        headerTitle.setText(user.getFname());
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(InterviewGdPrepActivity.this,drawerLayoutInterviewGdPrep,toolbar,R.string.drawer_open,R.string.drawer_close);

        drawerLayoutInterviewGdPrep.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Toast.makeText(InterviewGdPrepActivity.this,item.getTitle(),Toast.LENGTH_LONG).show();
                Intent intent;
                if (item.getTitle().equals("Home")){
                    intent=new Intent(InterviewGdPrepActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Dashboard")){
                    intent=new Intent(InterviewGdPrepActivity.this,DashboardActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Courses")){
                    intent=new Intent(InterviewGdPrepActivity.this,CoursesActivity.class);
                    startActivity(intent);
                }
                if(item.getTitle().equals("Topic Wise Prep")){
                    intent=new Intent(InterviewGdPrepActivity.this,PracticeActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Company Wise Prep")){
                    intent=new Intent(InterviewGdPrepActivity.this,CompanyWisePrepActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Salary Calculator")){
                    intent=new Intent(InterviewGdPrepActivity.this,SalaryCalculatorActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Resume Builder")) {
                    intent = new Intent(InterviewGdPrepActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Job Info")) {
                    intent = new Intent(InterviewGdPrepActivity.this, JobInfoActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Interview and GD Prep")) {
                    intent = new Intent(InterviewGdPrepActivity.this, InterviewGdPrepActivity.class);
                    startActivity(intent);
                }
                drawerLayoutInterviewGdPrep.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        MySingleton.release();
    }
}
