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
import android.widget.Toast;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.User;
import in.nfly.dell.nflydemo.fragments.KnowledgeBaseCompanyWiseFragment;
import in.nfly.dell.nflydemo.fragments.KnowledgeBaseJobWiseFragment;
import in.nfly.dell.nflydemo.adapters.ViewPagerAdapter;

public class KnowledgeBaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayoutKnowledgeBase;
    private TextView headerTitle;
    private TabLayout tabLayoutKnowledgeBase;
    private ViewPager viewPagerKnowledgeBase;
    private ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_base);
        drawerLayoutKnowledgeBase=findViewById(R.id.drawerLayoutKnowledgeBase);

        tabLayoutKnowledgeBase=findViewById(R.id.tabLayoutKnowledgeBase);
        viewPagerKnowledgeBase=findViewById(R.id.viewPagerKnowledgeBase);

        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragments(new KnowledgeBaseCompanyWiseFragment(),"Company Wise");
        viewPagerAdapter.addFragments(new KnowledgeBaseJobWiseFragment(),"Job Wise");

        viewPagerKnowledgeBase.setAdapter(viewPagerAdapter);
        tabLayoutKnowledgeBase.setupWithViewPager(viewPagerKnowledgeBase);

        Intent intent=getIntent();
        viewPagerKnowledgeBase.setCurrentItem(intent.getIntExtra("fragment_no",0));

        setToolbar();
        setNavigationDrawer();
    }
    private void setNavigationDrawer() {
        final NavigationView navigationView;
        navigationView = findViewById(R.id.navigationViewKnowledgeBase);

        User user=new User(KnowledgeBaseActivity.this);
        View header=navigationView. getHeaderView(0);
        headerTitle=header.findViewById(R.id.headerTitle);
        headerTitle.setText(user.getFname());
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(KnowledgeBaseActivity.this,drawerLayoutKnowledgeBase,toolbar,R.string.drawer_open,R.string.drawer_close);

        drawerLayoutKnowledgeBase.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Toast.makeText(KnowledgeBaseActivity.this,item.getTitle(),Toast.LENGTH_LONG).show();
                Intent intent;
                if (item.getTitle().equals("Home")){
                    intent=new Intent(KnowledgeBaseActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Dashboard")){
                    intent=new Intent(KnowledgeBaseActivity.this,DashboardActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Learn")){
                    intent=new Intent(KnowledgeBaseActivity.this,LearnActivity.class);
                    startActivity(intent);
                }
                if(item.getTitle().equals("Practice")){
                    intent=new Intent(KnowledgeBaseActivity.this,PracticeActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Knowledge Base")){
                    intent=new Intent(KnowledgeBaseActivity.this,KnowledgeBaseActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Feedback")){
                    intent=new Intent(KnowledgeBaseActivity.this,FeedBackActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Salary Calculator")){
                    intent=new Intent(KnowledgeBaseActivity.this,SalaryCalculatorActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Profile")){
                    intent=new Intent(KnowledgeBaseActivity.this,ProfileActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Help")){
                    intent=new Intent(KnowledgeBaseActivity.this,HelpActivity.class);
                    startActivity(intent);
                }
                if(item.getTitle().equals("Sign Out")){
                    User user=new User(KnowledgeBaseActivity.this);
                    user.logOut();
                    intent=new Intent(KnowledgeBaseActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
                drawerLayoutKnowledgeBase.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
    private void setToolbar() {
        toolbar=findViewById(R.id.knowledgeBaseToolbar);
        toolbar.setTitle("Knowledge Base");
        toolbar.setTitleTextColor(Color.WHITE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MySingleton.release();
    }
}
