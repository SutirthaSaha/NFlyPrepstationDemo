package in.nfly.dell.nflydemo.fragments.CompanyWiseFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyDetailsMockTestsFragment extends Fragment {
    
    public String company_id,company_name;
    private String urlPracticePapers="http://nfly.in/gapi/load_rows_one";

    private RecyclerView companyMockTestRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private TextView companyNoTestText;

    private ArrayList<String> titleDataSet=new ArrayList<String>(){};
    private ArrayList<String> idDataSet=new ArrayList<String>(){};
    private ArrayList<String> numQuesDataSet=new ArrayList<String>(){};
    private ArrayList<String> durationDataSet=new ArrayList<String>(){};
    
    public CompanyDetailsMockTestsFragment() {
        // Required empty public constructor
    }

    public static CompanyDetailsMockTestsFragment  newInstance(String company_id, String company_name) {
        CompanyDetailsMockTestsFragment fragment = new CompanyDetailsMockTestsFragment();
        fragment.company_id=company_id;
        fragment.company_name=company_name;
        return fragment;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_company_details_mock_tests, container, false);
    }

}
