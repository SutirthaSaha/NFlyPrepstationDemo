package in.nfly.dell.nflydemo.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.adapters.LearnPapersAdapter;
import in.nfly.dell.nflydemo.fragments.CompanyDetailsCompanyIntroFragment;
import in.nfly.dell.nflydemo.adapters.ViewPagerAdapter;
import in.nfly.dell.nflydemo.fragments.CompanyDetailsHiringProcessFragment;
import in.nfly.dell.nflydemo.fragments.CompanyDetailsInterviewExpFragment;
import in.nfly.dell.nflydemo.fragments.CompanyDetailsVideosFragment;

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

        viewPagerAdapter.addFragments(CompanyDetailsCompanyIntroFragment.newInstance(company_id,company_name),"Company Intro");
        viewPagerAdapter.addFragments(CompanyDetailsHiringProcessFragment.newInstance(company_id,company_name),"Hiring Process");
        viewPagerAdapter.addFragments(CompanyDetailsVideosFragment.newInstance(company_id,company_name),"Videos");
        viewPagerAdapter.addFragments(CompanyDetailsInterviewExpFragment.newInstance(company_id,company_name),"Interview Experience");

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
