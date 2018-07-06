package in.nfly.dell.nflydemo.activities.singleActivities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.adapters.ViewPagerAdapter;
import in.nfly.dell.nflydemo.fragments.LearnGDAgainstFragment;
import in.nfly.dell.nflydemo.fragments.LearnGDForFragment;
import in.nfly.dell.nflydemo.fragments.LearnInterviewQAEasyFragment;

public class LearnGDForAgainstActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String for_logic,against_logic,topic_name;
    
    private TabLayout tabLayoutLearnGDForAgainst;
    private ViewPager viewPagerLearnGDForAgainst;
    private ViewPagerAdapter viewPagerAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_gdfor_against);

        Intent intent=getIntent();
        for_logic=intent.getStringExtra("for_logic");
        against_logic=intent.getStringExtra("against_logic");
        topic_name=intent.getStringExtra("topic_name");

        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tabLayoutLearnGDForAgainst=findViewById(R.id.tabLayoutLearnGDForAgainst);
        viewPagerLearnGDForAgainst=findViewById(R.id.viewPagerLearnGDForAgainst);

        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragments(LearnGDForFragment.newInstance(for_logic),"For");
        viewPagerAdapter.addFragments(LearnGDAgainstFragment.newInstance(against_logic),"Against");

        viewPagerLearnGDForAgainst.setAdapter(viewPagerAdapter);
        tabLayoutLearnGDForAgainst.setupWithViewPager(viewPagerLearnGDForAgainst);

    }

    private void setToolbar() {
        toolbar=findViewById(R.id.learnGdForAgainstToolbar);
        toolbar.setTitle(topic_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }

}
