package in.nfly.dell.nflydemo.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

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

        viewPagerAdapter.addFragments(new CompanyDetailsCompanyIntroFragment(),"Overview");
        viewPagerAdapter.addFragments(new CompanyDetailsCompanyIntroFragment(),"Stats");
        viewPagerAdapter.addFragments(new CompanyDetailsCompanyIntroFragment(),"Career Path");
        viewPagerAdapter.addFragments(new CompanyDetailsCompanyIntroFragment(),"Resources");
        viewPagerAdapter.addFragments(new CompanyDetailsCompanyIntroFragment(),"Career Advise");
        viewPagerAdapter.addFragments(new CompanyDetailsCompanyIntroFragment(),"Videos");
        viewPagerAdapter.addFragments(new CompanyDetailsCompanyIntroFragment(),"FAQs");

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
