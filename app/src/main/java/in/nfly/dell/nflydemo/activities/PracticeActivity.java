package in.nfly.dell.nflydemo.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
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
import in.nfly.dell.nflydemo.fragments.PracticeCompanyWiseFragment;
import in.nfly.dell.nflydemo.fragments.PracticeExamWiseFragment;
import in.nfly.dell.nflydemo.fragments.PracticeTestSeriesFragment;
import in.nfly.dell.nflydemo.fragments.PracticeTopicWiseFragment;

public class PracticeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayoutPractice;
    private TextView headerTitle;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment=null;
            switch (item.getItemId()) {
                case R.id.practice_navigation_test_series:
                    fragment=new PracticeTestSeriesFragment();
                    return loadFragment(fragment);

                case R.id.practice_navigation_company_wise:
                    fragment=new PracticeCompanyWiseFragment();
                    return loadFragment(fragment);

                case R.id.practice_navigation_topic_wise:
                    fragment=new PracticeTopicWiseFragment();
                    return loadFragment(fragment);

                case R.id.practice_navigation_exam_wise:
                    fragment=new PracticeExamWiseFragment();
                    return loadFragment(fragment);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        BottomNavigationView navigation =findViewById(R.id.practiceBottomNavigation);
        BottomNavigationViewHelper.removeShiftMode(navigation);

        Intent intent=getIntent();
        onNavigationItemSelected(intent.getIntExtra("fragment_id",R.id.practice_navigation_company_wise));

        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        drawerLayoutPractice=findViewById(R.id.drawerLayoutPractice);
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

        User user=new User(PracticeActivity.this);
        View header=navigationView. getHeaderView(0);
        headerTitle=header.findViewById(R.id.headerTitle);
        headerTitle.setText(user.getFname());

        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(PracticeActivity.this,drawerLayoutPractice,toolbar,R.string.drawer_open,R.string.drawer_close);

        drawerLayoutPractice.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(PracticeActivity.this,item.getTitle(),Toast.LENGTH_LONG).show();
                Intent intent;
                if (item.getTitle().equals("Home")){
                    intent=new Intent(PracticeActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Dashboard")){
                    intent=new Intent(PracticeActivity.this,DashboardActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Courses")){
                    intent=new Intent(PracticeActivity.this,CoursesActivity.class);
                    startActivity(intent);
                }
                if(item.getTitle().equals("Topic Wise Prep")){
                    intent=new Intent(PracticeActivity.this,PracticeActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Company Wise Prep")){
                    intent=new Intent(PracticeActivity.this,CompanyWisePrepActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Salary Calculator")){
                    intent=new Intent(PracticeActivity.this,SalaryCalculatorActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Resume Builder")) {
                    intent = new Intent(PracticeActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Job Info")) {
                    intent = new Intent(PracticeActivity.this, JobInfoActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Interview and GD Prep")) {
                    intent = new Intent(PracticeActivity.this, InterviewGdPrepActivity.class);
                    startActivity(intent);
                }
                drawerLayoutPractice.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.practiceToolbar);
        toolbar.setTitle("Topic Wise Prep");
        toolbar.setTitleTextColor(Color.WHITE);
    }

    private boolean loadFragment(Fragment fragment){
        if(fragment!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.practiceFragmentContainer,fragment).commit();
            return true;
        }
        else{
            Toast.makeText(this, "Null Frag", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public boolean onNavigationItemSelected(int id){
        Fragment fragment=null;
        switch (id) {
            case R.id.practice_navigation_test_series:
                fragment=new PracticeTestSeriesFragment();
                return loadFragment(fragment);

            case R.id.practice_navigation_company_wise:
                fragment=new PracticeCompanyWiseFragment();
                return loadFragment(fragment);

            case R.id.practice_navigation_topic_wise:
                fragment=new PracticeTopicWiseFragment();
                return loadFragment(fragment);

            case R.id.practice_navigation_exam_wise:
                fragment=new PracticeExamWiseFragment();
                return loadFragment(fragment);
        }
        return false;
    }
}
