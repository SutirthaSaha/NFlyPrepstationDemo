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
import in.nfly.dell.nflydemo.fragments.LearnCourseFragment;
import in.nfly.dell.nflydemo.fragments.LearnGDFragment;
import in.nfly.dell.nflydemo.fragments.LearnInterviewFragment;
import in.nfly.dell.nflydemo.fragments.LearnPaperFragment;
import in.nfly.dell.nflydemo.fragments.LearnTipsFragment;

public class LearnActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayoutLearn;
    private TextView headerTitle;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment=null;
            switch (item.getItemId()) {
                case R.id.learn_navigation_course:
                    fragment=new LearnCourseFragment();
                    return loadFragment(fragment);

                case R.id.learn_navigation_interview:
                    fragment=new LearnInterviewFragment();
                    return loadFragment(fragment);

                case R.id.learn_navigation_gd:
                    fragment=new LearnGDFragment();
                    return loadFragment(fragment);

                case R.id.learn_navigation_papers:
                    fragment=new LearnPaperFragment();
                    return loadFragment(fragment);

                case R.id.learn_navigation_tips:
                    fragment=new LearnTipsFragment();
                    return loadFragment(fragment);
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        BottomNavigationView navigation =findViewById(R.id.learnBottomNavigation);
        BottomNavigationViewHelper.removeShiftMode(navigation);

        Intent intent=getIntent();
        onNavigationItemSelected(intent.getIntExtra("fragment_id",R.id.learn_navigation_course));
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        drawerLayoutLearn=findViewById(R.id.drawerLayoutLearn);
        setToolbar();
        setNavigationDrawer();
    }

    private boolean loadFragment(Fragment fragment){
        if(fragment!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.learnFragmentContainer,fragment).commit();
            return true;
        }
        else{
            Toast.makeText(this, "Null Frag", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void setNavigationDrawer() {
        final NavigationView navigationView;
        navigationView = findViewById(R.id.navigationViewLearn);

        User user=new User(LearnActivity.this);
        View header=navigationView. getHeaderView(0);
        headerTitle=header.findViewById(R.id.headerTitle);
        headerTitle.setText(user.getFname());
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(LearnActivity.this,drawerLayoutLearn,toolbar,R.string.drawer_open,R.string.drawer_close);

        drawerLayoutLearn.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Toast.makeText(LearnActivity.this,item.getTitle(),Toast.LENGTH_LONG).show();
                Intent intent;
                if (item.getTitle().equals("Home")){
                    intent=new Intent(LearnActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Dashboard")){
                    intent=new Intent(LearnActivity.this,DashboardActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Learn")){
                    intent=new Intent(LearnActivity.this,LearnActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Salary Calculator")){
                    intent=new Intent(LearnActivity.this,SalaryCalculatorActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Profile")){
                    intent=new Intent(LearnActivity.this,ProfileActivity.class);
                    startActivity(intent);
                }
                if(item.getTitle().equals("Practice")){
                    intent=new Intent(LearnActivity.this,TopicWiseActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Knowledge Base")){
                    intent=new Intent(LearnActivity.this,KnowledgeBaseActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Feedback")){
                    intent=new Intent(LearnActivity.this,FeedBackActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Help")){
                    intent=new Intent(LearnActivity.this,HelpActivity.class);
                    startActivity(intent);
                }
                if(item.getTitle().equals("Sign Out")){
                    User user=new User(LearnActivity.this);
                    user.logOut();
                    intent=new Intent(LearnActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                drawerLayoutLearn.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    public boolean onNavigationItemSelected(int id){
        Fragment fragment=null;
        switch (id) {
            case R.id.learn_navigation_course:
                fragment=new LearnCourseFragment();
                return loadFragment(fragment);

            case R.id.learn_navigation_interview:
                fragment=new LearnInterviewFragment();
                return loadFragment(fragment);

            case R.id.learn_navigation_gd:
                fragment=new LearnGDFragment();
                return loadFragment(fragment);

            case R.id.learn_navigation_papers:
                fragment=new LearnPaperFragment();
                return loadFragment(fragment);

            case R.id.learn_navigation_tips:
                fragment=new LearnTipsFragment();
                return loadFragment(fragment);
        }
        return false;
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.learnToolbar);
        toolbar.setTitle("Learn");
        toolbar.setTitleTextColor(Color.WHITE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MySingleton.release();
    }
}
