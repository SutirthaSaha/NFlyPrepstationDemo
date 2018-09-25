package in.nfly.dell.nflydemo.fragments.CompanyWiseFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.nfly.dell.nflydemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyDetailsSolvedPaperFragment extends Fragment {
    
    public String company_id,company_name;

    public CompanyDetailsSolvedPaperFragment() {
        // Required empty public constructor
    }
    
    public static CompanyDetailsSolvedPaperFragment  newInstance(String company_id, String company_name) {
        CompanyDetailsSolvedPaperFragment fragment = new CompanyDetailsSolvedPaperFragment();
        fragment.company_id=company_id;
        fragment.company_name=company_name;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_company_details_solved_paper, container, false);
    }

}
