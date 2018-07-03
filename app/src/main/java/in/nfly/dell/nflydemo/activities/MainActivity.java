package in.nfly.dell.nflydemo.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.User;
import in.nfly.dell.nflydemo.adapters.HomeIconsAdapter;
import in.nfly.dell.nflydemo.adapters.HomePrepHubAdapter;
import in.nfly.dell.nflydemo.adapters.HomeSwipeAdapter;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayoutHome;
    private Toolbar toolbar;
    private TextView headerTitle;
    private ViewPager viewPager;
    private HomeSwipeAdapter adapter;

    private RecyclerView HomeBannerIconsRecyclerView;
    private RecyclerView.Adapter homeiconsadapter;
    private RecyclerView.LayoutManager bannerlayoutManager;


    private RecyclerView HomeFeatureIconsRecyclerView;
    private RecyclerView.Adapter homeFeatureiconsadapter;
    private RecyclerView.LayoutManager homeFeaturelayoutManager;


    private static int currentPage = 0;
    private static int NUM_PAGES = 0;


    private String userDetails="http://nfly.in/gapi/get_details_one";
    private String email;
    private int[] swipe_image_resources= {R.drawable.imagevideo,R.drawable.imagepaper,R.drawable.imageresume};
    private String[] swipe_title_resources={"Video Courses","Placement Papers","Resume Builder"};


    private ArrayList<Integer> feature_icon_image_resources=new ArrayList<Integer>(){
        {
            add(R.drawable.colorvideo);
            add(R.drawable.coloredtest);
            add(R.drawable.home);
            add(R.drawable.interview);
            add(R.drawable.resume);
            add(R.drawable.resume);}};
    private ArrayList<String> feature_icon_title_resources=new ArrayList<String>(){
        {add("Video Courses");
            add("Weekly Test Series");
            add("Company/Topic Test");
            add("Placement Papers");
            add("Preparation Hub");
            add("Resume Builder");}};


    private ArrayList<Integer> banner_image_resources=new ArrayList<Integer>(){
        {add(R.drawable.gd);
        add(R.drawable.courses);
        add(R.drawable.papers);}};
    private ArrayList<String> banner_title_resources=new ArrayList<String>(){
        {add("2500+ happy users");
            add("200+ indepth videos");
            add("100+ articles");}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=getIntent();
        email=intent.getStringExtra("email");
        getUserDetails(email);
        setToolbar();
        drawerLayoutHome=findViewById(R.id.drawerLayoutHome);


        setToolbar();
        setNavigationDrawer();
        setViewPager();
        setBanner();
        setFeaturesIcons();
        setPrepHubIcons();
        setPractiseIcons();





    }

    private void setPractiseIcons()
    {
        RecyclerView PractiseRecyclerView;
        RecyclerView.Adapter Practiseiconsadapter;
        RecyclerView.LayoutManager PractiselayoutManager;

        PractiseRecyclerView=findViewById(R.id.homePractiseRecyclerView);
        PractiselayoutManager=new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false);
        PractiseRecyclerView.setLayoutManager(PractiselayoutManager);

        Practiseiconsadapter= new HomePrepHubAdapter(feature_icon_title_resources,feature_icon_image_resources);
        PractiseRecyclerView.setAdapter(Practiseiconsadapter);
    }

    private void setPrepHubIcons()
    {
        RecyclerView PrepHubIconsRecyclerView;
        RecyclerView.Adapter PrepHubiconsadapter;
        RecyclerView.LayoutManager PrepHublayoutManager;

        PrepHubIconsRecyclerView=findViewById(R.id.homePrepHubRecyclerView);
        PrepHublayoutManager=new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false);
        PrepHubIconsRecyclerView.setLayoutManager(PrepHublayoutManager);

        PrepHubiconsadapter= new HomePrepHubAdapter(feature_icon_title_resources,feature_icon_image_resources);
        PrepHubIconsRecyclerView.setAdapter(PrepHubiconsadapter);

    }

    private void setFeaturesIcons()
    {
        HomeFeatureIconsRecyclerView=findViewById(R.id.homeFeaturesIconsRecyclerView);
        homeFeaturelayoutManager=new GridLayoutManager(MainActivity.this,3);
        HomeFeatureIconsRecyclerView.setLayoutManager(homeFeaturelayoutManager);

        homeFeatureiconsadapter= new HomeIconsAdapter(feature_icon_title_resources,feature_icon_image_resources);
        HomeFeatureIconsRecyclerView.setAdapter(homeFeatureiconsadapter);
    }

    private void setBanner()
    {
        HomeBannerIconsRecyclerView=findViewById(R.id.homeBannerIconsRecyclerView);
        bannerlayoutManager=new GridLayoutManager(MainActivity.this,3);
        HomeBannerIconsRecyclerView.setLayoutManager(bannerlayoutManager);

        homeiconsadapter= new HomeIconsAdapter(banner_title_resources,banner_image_resources);
        HomeBannerIconsRecyclerView.setAdapter(homeiconsadapter);

    }

    private void setViewPager()
    {
        viewPager=findViewById(R.id.homeViewPager);
        adapter= new HomeSwipeAdapter(this,swipe_image_resources,swipe_title_resources);
        viewPager.setAdapter(adapter);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.homeSliderIndicator);
        indicator.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    int pageCount = swipe_title_resources.length;
                    if (currentPage == 0) {
                        viewPager.setCurrentItem(0, false);
                    } else if (currentPage == pageCount - 1) {
                        viewPager.setCurrentItem(pageCount-1, false);
                    }
                }
            }
        });
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

    }

    private void setNavigationDrawer() {
        final NavigationView navigationView;
        navigationView = findViewById(R.id.navigationViewHome);

        User user=new User(MainActivity.this);
        View header=navigationView. getHeaderView(0);
        headerTitle=header.findViewById(R.id.headerTitle);
        headerTitle.setText(user.getFname());
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(MainActivity.this,drawerLayoutHome,toolbar,R.string.drawer_open,R.string.drawer_close);

        drawerLayoutHome.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(MainActivity.this,item.getTitle(),Toast.LENGTH_LONG).show();
                Intent intent;
                if (item.getTitle().equals("Home")){
                    intent=new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Dashboard")){
                    intent=new Intent(MainActivity.this,DashboardActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Learn")){
                    intent=new Intent(MainActivity.this,LearnActivity.class);
                    startActivity(intent);
                }
                if(item.getTitle().equals("Practice")){
                    intent=new Intent(MainActivity.this,PracticeActivity.class);
                    startActivity(intent);
                }
                if (item.getTitle().equals("Knowledge Base")){
                    intent=new Intent(MainActivity.this,KnowledgeBaseActivity.class);
                    startActivity(intent);
                }
                if(item.getTitle().equals("Sign Out")){
                    User user=new User(MainActivity.this);
                    user.logOut();
                    intent=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
                drawerLayoutHome.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.homeToolbar);
        toolbar.setTitle("Home");
        toolbar.setTitleTextColor(Color.WHITE);
    }
    private void getUserDetails(final String  email) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,userDetails, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject;
                    arrayObject=new JSONObject(response);
                    String user_id=arrayObject.getString("user_id");
                    String fname=arrayObject.getString("fname");

                    SharedPreferences sharedPreferences=getSharedPreferences("Login Details", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("user_id",user_id);
                    editor.putString("fname",fname);
                    editor.putString("email",email);
                    editor.apply();

                    User user=new User(MainActivity.this);
                    Toast.makeText(MainActivity.this,user.getEmail()+"\n"+user.getFname()+"\n"+user.getUser_id(), Toast.LENGTH_SHORT).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
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
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<String, String>();
                params.put("key","email");
                params.put("value",email);
                params.put("table","user");
                return params;
            }
        };
        MySingleton.getmInstance(MainActivity.this).addToRequestQueue(stringRequest);
    }
}
