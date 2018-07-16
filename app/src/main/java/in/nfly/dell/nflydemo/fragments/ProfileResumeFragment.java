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
import in.nfly.dell.nflydemo.User;
import in.nfly.dell.nflydemo.adapters.ProfileResumeAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileResumeFragment extends Fragment {
    private RecyclerView profileResumeRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<String> titleDataSet=new ArrayList<String>(){{add("Template 1");add("Template 2");add("Template 3");}};
    private ArrayList<String> imageDataSet=new ArrayList<String>(){{add("http://nfly.in/assets/images/resume_templates/template1.png");add("http://nfly.in/assets/images/resume_templates/template3.png");add("http://nfly.in/assets/images/resume_templates/template4.png");}};
    private ArrayList<String> linkDataSet=new ArrayList<String>(){};
    private String user_id;
    public ProfileResumeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_profile_resume, container, false);
        User user=new User(getContext());
        user_id=user.getUser_id();

        linkDataSet.add("http://nfly.in/profileapi/resume/"+user_id+"/sdf5614dflfd5");
        linkDataSet.add("http://nfly.in/profileapi/resume3/"+user_id+"/sdf5614dflfd5");
        linkDataSet.add("http://nfly.in/profileapi/resume4/"+user_id+"/sdf5614dflfd5");

        profileResumeRecyclerView=v.findViewById(R.id.profileResumeRecyclerView);
        layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        profileResumeRecyclerView.setLayoutManager(layoutManager);
        adapter=new ProfileResumeAdapter(getContext(),titleDataSet,imageDataSet,linkDataSet);
        profileResumeRecyclerView.setAdapter(adapter);
        return v;
    }

}
