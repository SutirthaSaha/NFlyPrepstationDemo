package in.nfly.dell.nflydemo.activities.singleActivities;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
import in.nfly.dell.nflydemo.adapters.InterviewQuestionsAdapter;
import in.nfly.dell.nflydemo.adapters.ViewPagerAdapter;
import in.nfly.dell.nflydemo.fragments.LearnGDAgainstFragment;
import in.nfly.dell.nflydemo.fragments.LearnGDForFragment;
import in.nfly.dell.nflydemo.fragments.LearnInterviewQADifficultFragment;
import in.nfly.dell.nflydemo.fragments.LearnInterviewQAEasyFragment;
import in.nfly.dell.nflydemo.fragments.LearnInterviewQAMediumFragment;

public class LearnInterviewQAActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String subtopic_id,subtopic_name,question_level;

    private TabLayout tabLayoutLearnInterviewQA;
    private ViewPager viewPagerLearnInterviewQA;
    private ViewPagerAdapter viewPagerAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_interview_qa);

        Intent intent=getIntent();
        subtopic_id=intent.getStringExtra("subtopic_id");
        subtopic_name=intent.getStringExtra("subtopic_name");
        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tabLayoutLearnInterviewQA=findViewById(R.id.tabLayoutLearnInterviewQA);
        viewPagerLearnInterviewQA=findViewById(R.id.viewPagerLearnInterviewQA);

        viewPagerLearnInterviewQA.setOffscreenPageLimit(3);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragments(LearnInterviewQAEasyFragment.newInstance(subtopic_id,subtopic_name),"Easy");
        viewPagerAdapter.addFragments(LearnInterviewQAMediumFragment.newInstance(subtopic_id,subtopic_name),"Medium");
        viewPagerAdapter.addFragments(LearnInterviewQADifficultFragment.newInstance(subtopic_id,subtopic_name),"Difficult");

        viewPagerLearnInterviewQA.setAdapter(viewPagerAdapter);
        tabLayoutLearnInterviewQA.setupWithViewPager(viewPagerLearnInterviewQA);
        
    }
    private void setToolbar() {
        toolbar=findViewById(R.id.interviewQuestionsToolbar);
        toolbar.setTitle(subtopic_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }
}
