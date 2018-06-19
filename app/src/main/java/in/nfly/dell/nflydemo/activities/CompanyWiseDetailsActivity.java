package in.nfly.dell.nflydemo.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.fragments.CompanyDetailsCompanyIntroFragment;
import in.nfly.dell.nflydemo.adapters.ViewPagerAdapter;

public class CompanyWiseDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPagerCompanyWiseDetails;
    private TabLayout tabLayoutCompanyWiseDetails;
    private ViewPagerAdapter viewPagerAdapter;

    private String company_id,company_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_wise_details);

        Intent intent=getIntent();
        company_id=intent.getStringExtra("company_id");
        company_name=intent.getStringExtra("company_name");
        Toast.makeText(this, company_id, Toast.LENGTH_SHORT).show();
        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        viewPagerCompanyWiseDetails=findViewById(R.id.viewPagerCompanyWiseDetails);
        tabLayoutCompanyWiseDetails=findViewById(R.id.tabLayoutCompanyWiseDetails);

        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragments(new CompanyDetailsCompanyIntroFragment(),"Company Intro");
        viewPagerAdapter.addFragments(new CompanyDetailsCompanyIntroFragment(),"Hiring Process");
        viewPagerAdapter.addFragments(new CompanyDetailsCompanyIntroFragment(),"Videos");
        viewPagerAdapter.addFragments(new CompanyDetailsCompanyIntroFragment(),"Interview Experience");

        viewPagerCompanyWiseDetails.setAdapter(viewPagerAdapter);
        tabLayoutCompanyWiseDetails.setupWithViewPager(viewPagerCompanyWiseDetails);
    }
    private void setToolbar() {
        toolbar=findViewById(R.id.companyWiseDetailsToolbar);
        toolbar.setTitle(company_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }
}
