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

public class CompanyWisePrepActivity extends AppCompatActivity {
    private DrawerLayout drawerLayoutCompanyWisePrep;
    private Toolbar toolbar;
    private TextView headerTitle;
    private RecyclerView companyWisePrepRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> titleDataSet=new ArrayList<String>(){{add("IT Companies");add("Core Companies");add("Start-ups");add("Dream Companies");}};
    private ArrayList<String> imageDataSet=new ArrayList<String>(){{add(Integer.toString(R.drawable.learn_interview_company_wise));add(Integer.toString(R.drawable.ic_computer_white));add(Integer.toString(R.drawable.ic_computer_white));add(Integer.toString(R.drawable.ic_computer_white));}};
    private ArrayList<String> idDataSet=new ArrayList<String>(){{add("1");add("2");add("3");add("4");}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_wise_prep);
        drawerLayoutCompanyWisePrep=findViewById(R.id.companyWisePrepDrawerLayout);
        companyWisePrepRecyclerView=findViewById(R.id.companyWisePrepRecyclerView);
        layoutManager=new GridLayoutManager(CompanyWisePrepActivity.this,2);
        companyWisePrepRecyclerView.setLayoutManager(layoutManager);
        setToolbar();
        setNavigationDrawer();
        setValues();
    }

    private void setValues() {
        adapter=new CompanyTypesAdapter(CompanyWisePrepActivity.this,titleDataSet,imageDataSet,idDataSet);
        companyWisePrepRecyclerView.setAdapter(adapter);
    }

    private void setNavigationDrawer() {
        final NavigationView navigationView;
        navigationView = findViewById(R.id.navigationViewCompanyWisePrep);

        User user=new User(CompanyWisePrepActivity.this);
        View header=navigationView. getHeaderView(0);
        headerTitle=header.findViewById(R.id.headerTitle);
        headerTitle.setText(user.getFname());
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(CompanyWisePrepActivity.this,drawerLayoutCompanyWisePrep,toolbar,R.string.drawer_open,R.string.drawer_close);

        drawerLayoutCompanyWisePrep.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(CompanyWisePrepActivity.this,item.getTitle(),Toast.LENGTH_LONG).show();
                Intent intent;
                if (item.getTitle().equals("Home")){
                    intent=new Intent(CompanyWisePrepActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Dashboard")){
                    intent=new Intent(CompanyWisePrepActivity.this,DashboardActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Exam Wise Prep")){
                    intent=new Intent(CompanyWisePrepActivity.this,ExamWisePrepActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Courses")){
                    intent=new Intent(CompanyWisePrepActivity.this,CoursesActivity.class);
                    startActivity(intent);
                }
                if(item.getTitle().equals("Topic Wise Prep")){
                    intent=new Intent(CompanyWisePrepActivity.this,TopicWiseActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Company Wise Prep")){
                    intent=new Intent(CompanyWisePrepActivity.this,CompanyWisePrepActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Salary Calculator")){
                    intent=new Intent(CompanyWisePrepActivity.this,SalaryCalculatorActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Resume Builder")) {
                    intent = new Intent(CompanyWisePrepActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Job Info")) {
                    intent = new Intent(CompanyWisePrepActivity.this, JobInfoActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Interview and GD Prep")) {
                    intent = new Intent(CompanyWisePrepActivity.this, InterviewGdPrepActivity.class);
                    startActivity(intent);
                }
                drawerLayoutCompanyWisePrep.closeDrawer(GravityCompat.START);
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
            intent=new Intent(CompanyWisePrepActivity.this,FeedBackActivity.class);
            startActivity(intent);
        }
        if (item.getTitle().equals("Help")){
            intent=new Intent(CompanyWisePrepActivity.this,HelpActivity.class);
            startActivity(intent);
        }
        if(item.getTitle().equals("Sign Out")) {
            User user = new User(CompanyWisePrepActivity.this);
            user.logOut();
            intent = new Intent(CompanyWisePrepActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MySingleton.release();
        companyWisePrepRecyclerView.setAdapter(null);
    }
    private void setToolbar() {
        toolbar=findViewById(R.id.companyWisePrepToolbar);
        toolbar.setTitle("Company Wise Prep");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
    }

}
