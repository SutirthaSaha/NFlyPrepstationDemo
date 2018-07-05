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

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.User;
import in.nfly.dell.nflydemo.adapters.DashBoardAdapter;

public class DashboardActivity extends AppCompatActivity {

    private DrawerLayout drawerLayoutDashboard;
    private Toolbar toolbar;
    private TextView headerTitle;
    private ArrayList<String> number_data_set=new ArrayList<String>(){
        { add("23/50"); }};
    private ArrayList<String> title_data_set=new ArrayList<String>(){
        { add("Title"); }};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        drawerLayoutDashboard=findViewById(R.id.drawerLayoutDashboard);
        setToolbar();
        setNavigationDrawer();
        setCards();
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
    private void setCards()
    {


        RecyclerView DashboardRecyclerView;
        RecyclerView.Adapter dashboardAdapter;
        RecyclerView.LayoutManager PrepHublayoutManager;

        DashboardRecyclerView=findViewById(R.id.dashboardRecyclerView);
        PrepHublayoutManager=new LinearLayoutManager(DashboardActivity.this, LinearLayoutManager.HORIZONTAL,false);
        DashboardRecyclerView.setLayoutManager(PrepHublayoutManager);

        dashboardAdapter= new DashBoardAdapter(title_data_set,number_data_set);
        DashboardRecyclerView.setAdapter(dashboardAdapter);
    }
}
