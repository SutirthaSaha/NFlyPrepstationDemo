package in.nfly.dell.nflydemo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.adapters.LearnPapersAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PracticeCompanyWiseFragment extends Fragment {
    private RecyclerView practiceCompanyWiseRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> titleDataSet=new ArrayList<String>(){{add("HR Questions");add("Software Tools");add("Eng. Topics");add("Company wise");add("Puzzles");add("Miscellaneous");}};
    private ArrayList<String> imageDataSet=new ArrayList<String>(){{add(Integer.toString(R.drawable.ic_computer_black));add(Integer.toString(R.drawable.ic_computer_black));add(Integer.toString(R.drawable.ic_computer_black));add(Integer.toString(R.drawable.ic_computer_black));add(Integer.toString(R.drawable.ic_computer_black));add(Integer.toString(R.drawable.ic_computer_black));}};
    private ArrayList<String> numberDataSet=new ArrayList<String>(){{add(Integer.toString(4));add(Integer.toString(4));add(Integer.toString(4));add(Integer.toString(4));add(Integer.toString(4));add(Integer.toString(4));}};


    public PracticeCompanyWiseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_practice_company_wise, container, false);
        practiceCompanyWiseRecyclerView=v.findViewById(R.id.practiceCompanyWiseRecyclerView);
        layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        practiceCompanyWiseRecyclerView.setLayoutManager(layoutManager);
        adapter=new LearnPapersAdapter(titleDataSet,imageDataSet,numberDataSet,getContext());
        practiceCompanyWiseRecyclerView.setAdapter(adapter);
        return v;
    }

}
