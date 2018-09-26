package in.nfly.dell.nflydemo.activities.singleActivities;

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
import in.nfly.dell.nflydemo.adapters.ViewPagerAdapter;
import in.nfly.dell.nflydemo.fragments.ExamWiseFragments.ExamWiseApplicationProcessFragment;
import in.nfly.dell.nflydemo.fragments.ExamWiseFragments.ExamWiseBasicInfoFragment;
import in.nfly.dell.nflydemo.fragments.ExamWiseFragments.ExamWiseEligibilityFragment;
import in.nfly.dell.nflydemo.fragments.ExamWiseFragments.ExamWiseFAQFragment;
import in.nfly.dell.nflydemo.fragments.ExamWiseFragments.ExamWiseMockPapersFragment;
import in.nfly.dell.nflydemo.fragments.ExamWiseFragments.ExamWiseStrategyFragment;
import in.nfly.dell.nflydemo.fragments.ExamWiseFragments.ExamWiseSyllabusFragment;

public class ExamWiseDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    String exam_id,exam_name;
    private ViewPager viewPagerExamWiseDetails;
    private TabLayout tabLayoutExamWiseDetails;
    private ViewPagerAdapter viewPagerAdapter;

    private String urlExam="http://nfly.in/gapi/load_rows_one";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_wise_details);
        Intent intent=getIntent();
        exam_id=intent.getStringExtra("exam_id");
        exam_name=intent.getStringExtra("exam_name");
        Toast.makeText(this, exam_id, Toast.LENGTH_SHORT).show();
        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        viewPagerExamWiseDetails=findViewById(R.id.viewPagerExamWiseDetails);
        tabLayoutExamWiseDetails=findViewById(R.id.tabLayoutExamWiseDetails);

        viewPagerExamWiseDetails.setOffscreenPageLimit(10);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragments(ExamWiseBasicInfoFragment.newInstance(exam_id,exam_name),"Basic Info");
        viewPagerAdapter.addFragments(ExamWiseSyllabusFragment.newInstance(exam_id,exam_name),"Syllabus and Pattern");
        viewPagerAdapter.addFragments(ExamWiseEligibilityFragment.newInstance(exam_id,exam_name),"Eligibility");
        viewPagerAdapter.addFragments(ExamWiseApplicationProcessFragment.newInstance(exam_id,exam_name),"Application Process");
        viewPagerAdapter.addFragments(ExamWiseMockPapersFragment.newInstance(exam_id,exam_name),"Mock Papers");
        viewPagerAdapter.addFragments(ExamWiseStrategyFragment.newInstance(exam_id,exam_name),"Strategy");
        viewPagerAdapter.addFragments(ExamWiseFAQFragment.newInstance(exam_id,exam_name),"FAQs");


        //viewPagerAdapter.addFragments(CompanyDetailsHiringProcessFragment.newInstance(company_id,company_name),"Hiring Process");
        //viewPagerAdapter.addFragments(CompanyDetailsVideosFragment.newInstance(company_id,company_name),"Videos");

        viewPagerExamWiseDetails.setAdapter(viewPagerAdapter);
        tabLayoutExamWiseDetails.setupWithViewPager(viewPagerExamWiseDetails);
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.examWiseDetailsToolbar);
        toolbar.setTitle(exam_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }

}
