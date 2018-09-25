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
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.User;
import in.nfly.dell.nflydemo.adapters.ViewPagerAdapter;
import in.nfly.dell.nflydemo.fragments.LearnGDFragment;
import in.nfly.dell.nflydemo.fragments.LearnInterviewFragment;
import in.nfly.dell.nflydemo.fragments.PracticeCompanyWiseFragment;
import in.nfly.dell.nflydemo.fragments.PracticeExamWiseFragment;
import in.nfly.dell.nflydemo.fragments.PracticeTestSeriesFragment;
import in.nfly.dell.nflydemo.fragments.PracticeTopicWiseFragment;
import in.nfly.dell.nflydemo.fragments.TopicWiseFragments.TopicWiseLRFragment;
import in.nfly.dell.nflydemo.fragments.TopicWiseFragments.TopicWiseQuantFragment;
import in.nfly.dell.nflydemo.fragments.TopicWiseFragments.TopicWiseTechFragment;
import in.nfly.dell.nflydemo.fragments.TopicWiseFragments.TopicWiseVerbalFragment;

public class TopicWiseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayoutPractice;
    private TextView headerTitle;
    private TabLayout tabLayoutTopicWise;
    private ViewPager viewPagerTopicWise;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_wise);

        drawerLayoutPractice=findViewById(R.id.drawerLayoutPractice);
        tabLayoutTopicWise=findViewById(R.id.tabLayoutTopicWise);
        viewPagerTopicWise=findViewById(R.id.viewPagerTopicWise);

        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragments(new TopicWiseQuantFragment(),"Quantitative");
        viewPagerAdapter.addFragments(new TopicWiseLRFragment(),"Logical Reasoning");
        viewPagerAdapter.addFragments(new TopicWiseVerbalFragment(),"Verbal");
        viewPagerAdapter.addFragments(new TopicWiseTechFragment(),"Technical");

        viewPagerTopicWise.setAdapter(viewPagerAdapter);
        tabLayoutTopicWise.setupWithViewPager(viewPagerTopicWise);

        Intent intent=getIntent();
        viewPagerTopicWise.setCurrentItem(intent.getIntExtra("fragment_no",0));

        setToolbar();
        setNavigationDrawer();
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
                drawerLayoutPractice.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.practiceToolbar);
        toolbar.setTitle("Topic Wise Prep");
        toolbar.setTitleTextColor(Color.WHITE);
    }
}
