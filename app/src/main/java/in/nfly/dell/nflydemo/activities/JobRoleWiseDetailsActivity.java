package in.nfly.dell.nflydemo.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import in.nfly.dell.nflydemo.JobWiseFragments.JobRoleCareerAdviseFragment;
import in.nfly.dell.nflydemo.JobWiseFragments.JobRoleCareerPathFragment;
import in.nfly.dell.nflydemo.JobWiseFragments.JobRoleFAQsFragment;
import in.nfly.dell.nflydemo.JobWiseFragments.JobRoleOverviewFragment;
import in.nfly.dell.nflydemo.JobWiseFragments.JobRoleResourcesFragment;
import in.nfly.dell.nflydemo.JobWiseFragments.JobRoleStatsFragment;
import in.nfly.dell.nflydemo.JobWiseFragments.JobRoleVideosFragment;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.adapters.ViewPagerAdapter;
import in.nfly.dell.nflydemo.fragments.CompanyDetailsCompanyIntroFragment;

public class JobRoleWiseDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPagerJobWiseDetails;
    private TabLayout tabLayoutJobWiseDetails;
    private ViewPagerAdapter viewPagerAdapter;

    private String job_role_id,job_role_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_role_wise_details);

        Intent intent=getIntent();
        job_role_id=intent.getStringExtra("job_role_id");
        job_role_name=intent.getStringExtra("job_role_name");

        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        viewPagerJobWiseDetails=findViewById(R.id.viewPagerJobWiseDetails);
        tabLayoutJobWiseDetails=findViewById(R.id.tabLayoutJobWiseDetails);

        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragments(JobRoleOverviewFragment.newInstance(job_role_id,job_role_name),"Overview");
        viewPagerAdapter.addFragments(JobRoleStatsFragment.newInstance(job_role_id,job_role_name),"Stats");
        viewPagerAdapter.addFragments(JobRoleCareerPathFragment.newInstance(job_role_id,job_role_name),"Career Path");
        viewPagerAdapter.addFragments(JobRoleResourcesFragment.newInstance(job_role_id,job_role_name),"Resources");
        viewPagerAdapter.addFragments(JobRoleCareerAdviseFragment.newInstance(job_role_id,job_role_name),"Career Advise");
        viewPagerAdapter.addFragments(JobRoleVideosFragment.newInstance(job_role_id,job_role_name),"Videos");
        viewPagerAdapter.addFragments(JobRoleFAQsFragment.newInstance(job_role_id,job_role_name),"FAQs");

        viewPagerJobWiseDetails.setAdapter(viewPagerAdapter);
        tabLayoutJobWiseDetails.setupWithViewPager(viewPagerJobWiseDetails);

    }
    private void setToolbar() {
        toolbar=findViewById(R.id.jobWiseDetailsToolbar);
        toolbar.setTitle(job_role_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }
}
