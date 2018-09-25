package in.nfly.dell.nflydemo.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.fragments.CompanyWiseFragments.CompanyDetailsApplicationProcessFragment;
import in.nfly.dell.nflydemo.fragments.CompanyWiseFragments.CompanyDetailsCompanyIntroFragment;
import in.nfly.dell.nflydemo.adapters.ViewPagerAdapter;
import in.nfly.dell.nflydemo.fragments.CompanyWiseFragments.CompanyDetailsEligibilityFragment;
import in.nfly.dell.nflydemo.fragments.CompanyWiseFragments.CompanyDetailsExamInfoFragment;
import in.nfly.dell.nflydemo.fragments.CompanyWiseFragments.CompanyDetailsHiringProcessFragment;
import in.nfly.dell.nflydemo.fragments.CompanyWiseFragments.CompanyDetailsInterviewExpFragment;
import in.nfly.dell.nflydemo.fragments.CompanyWiseFragments.CompanyDetailsInterviewQuesFragment;
import in.nfly.dell.nflydemo.fragments.CompanyWiseFragments.CompanyDetailsMockTestsFragment;
import in.nfly.dell.nflydemo.fragments.CompanyWiseFragments.CompanyDetailsSolvedPaperFragment;
import in.nfly.dell.nflydemo.fragments.CompanyWiseFragments.CompanyDetailsStrategyFragment;
import in.nfly.dell.nflydemo.fragments.CompanyWiseFragments.CompanyDetailsSyllabusFragment;
import in.nfly.dell.nflydemo.fragments.CompanyWiseFragments.CompanyDetailsVideosFragment;

public class CompanyWiseDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPagerCompanyWiseDetails;
    private TabLayout tabLayoutCompanyWiseDetails;
    private ViewPagerAdapter viewPagerAdapter;

    private String company_id,company_name;
    private String urlCompany="http://nfly.in/gapi/load_rows_one";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_wise_details);

        Intent intent=getIntent();
        company_id=intent.getStringExtra("company_id");
        company_name=intent.getStringExtra("company_name");
        //Toast.makeText(this, company_id, Toast.LENGTH_SHORT).show();
        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        viewPagerCompanyWiseDetails=findViewById(R.id.viewPagerCompanyWiseDetails);
        tabLayoutCompanyWiseDetails=findViewById(R.id.tabLayoutCompanyWiseDetails);

        viewPagerCompanyWiseDetails.setOffscreenPageLimit(10);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragments(CompanyDetailsCompanyIntroFragment.newInstance(company_id,company_name),"Company Intro");
        viewPagerAdapter.addFragments(CompanyDetailsExamInfoFragment.newInstance(company_id,company_name),"Exam Info");
        viewPagerAdapter.addFragments(CompanyDetailsSyllabusFragment.newInstance(company_id,company_name),"Syllabus");
        viewPagerAdapter.addFragments(CompanyDetailsEligibilityFragment.newInstance(company_id,company_name),"Eligibility");
        viewPagerAdapter.addFragments(CompanyDetailsApplicationProcessFragment.newInstance(company_id,company_name),"Application Process");
        viewPagerAdapter.addFragments(CompanyDetailsStrategyFragment.newInstance(company_id,company_name),"Strategy");
        viewPagerAdapter.addFragments(CompanyDetailsMockTestsFragment.newInstance(company_id,company_name),"Mock Tests");
        viewPagerAdapter.addFragments(CompanyDetailsSolvedPaperFragment.newInstance(company_id,company_name),"Solved Papers");
        viewPagerAdapter.addFragments(CompanyDetailsInterviewQuesFragment.newInstance(company_id,company_name),"Interview Questions");
        viewPagerAdapter.addFragments(CompanyDetailsInterviewExpFragment.newInstance(company_id,company_name),"Interview Experience");

        //viewPagerAdapter.addFragments(CompanyDetailsHiringProcessFragment.newInstance(company_id,company_name),"Hiring Process");
        //viewPagerAdapter.addFragments(CompanyDetailsVideosFragment.newInstance(company_id,company_name),"Videos");

        viewPagerCompanyWiseDetails.setAdapter(viewPagerAdapter);
        tabLayoutCompanyWiseDetails.setupWithViewPager(viewPagerCompanyWiseDetails);
    }
    private void setToolbar() {
        toolbar=findViewById(R.id.companyWiseDetailsToolbar);
        toolbar.setTitle(company_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MySingleton.release();
    }
}
