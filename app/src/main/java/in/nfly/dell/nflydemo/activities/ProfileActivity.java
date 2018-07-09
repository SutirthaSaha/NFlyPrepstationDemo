package in.nfly.dell.nflydemo.activities;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.adapters.ViewPagerAdapter;
import in.nfly.dell.nflydemo.fragments.ProfileAcademicFragment;
import in.nfly.dell.nflydemo.fragments.ProfilePersonalityFragment;
import in.nfly.dell.nflydemo.fragments.ProfileResumeFragment;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayoutProfile;
    private ViewPager viewPagerProfile;
    private ViewPagerAdapter viewPagerAdapter;
    private ProgressBar profileProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        profileProgressBar=findViewById(R.id.profileProgressBar);
        tabLayoutProfile=findViewById(R.id.tabLayoutProfile);
        viewPagerProfile=findViewById(R.id.viewPagerProfile);

        profileProgressBar.setProgress(10);
        viewPagerProfile.setOffscreenPageLimit(5);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragments(new ProfileAcademicFragment(),"Personal");
        viewPagerAdapter.addFragments(new ProfileAcademicFragment(),"Academic");
        viewPagerAdapter.addFragments(new ProfilePersonalityFragment(),"Personality");
        viewPagerAdapter.addFragments(new ProfileResumeFragment(),"Resume");

        viewPagerProfile.setAdapter(viewPagerAdapter);
        tabLayoutProfile.setupWithViewPager(viewPagerProfile);
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.profileToolbar);
        toolbar.setTitle("Profile");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }
}
