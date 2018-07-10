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
import in.nfly.dell.nflydemo.activities.MainActivity;
import in.nfly.dell.nflydemo.adapters.ProfileAcademicAchievementsAdapter;
import in.nfly.dell.nflydemo.adapters.ProfileAcademicProjectsAdapter;
import in.nfly.dell.nflydemo.adapters.ProfileAcademicTrainingsAdapter;
import in.nfly.dell.nflydemo.adapters.ProfileAcademicWorkExperienceAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileAcademicFragment extends Fragment {

    private RecyclerView workExperienceRecyclerView;
    private RecyclerView trainingsRecyclerView;
    private RecyclerView projectsRecyclerView;
    private RecyclerView achievementsRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<String> DataSet=new ArrayList<String>(){
        {  add("blahblah");
            add("blah");
         }};


    public ProfileAcademicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view;
        view= inflater.inflate(R.layout.fragment_profile_academic, container, false);

        setWorkExperience(view);
        setTrainings(view);
        setProjects(view);
        setAchievements(view);
        return view;
    }

    private void setAchievements(View v)
    {

        achievementsRecyclerView=v.findViewById(R.id.profileAcademicAchievementsRecyclerView);
        layoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        achievementsRecyclerView.setLayoutManager(layoutManager);

        adapter= new ProfileAcademicAchievementsAdapter(DataSet,DataSet);
        achievementsRecyclerView.setAdapter(adapter);
    }


    private void setWorkExperience(View v)
    {

        workExperienceRecyclerView=v.findViewById(R.id.profileAcademicWorkExperienceRecyclerView);
        layoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        workExperienceRecyclerView.setLayoutManager(layoutManager);

        adapter= new ProfileAcademicWorkExperienceAdapter(DataSet,DataSet,DataSet,DataSet,DataSet,DataSet);
        workExperienceRecyclerView.setAdapter(adapter);
    }

    private void setTrainings(View v)
    {

        trainingsRecyclerView=v.findViewById(R.id.profileAcademicTrainingsRecyclerView);
        layoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        trainingsRecyclerView.setLayoutManager(layoutManager);

        adapter= new ProfileAcademicTrainingsAdapter(DataSet,DataSet,DataSet,DataSet);
        trainingsRecyclerView.setAdapter(adapter);
    }


    private void setProjects(View v)
    {
        projectsRecyclerView=v.findViewById(R.id.profileAcademicProjectsRecyclerView);
        layoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        projectsRecyclerView.setLayoutManager(layoutManager);

        adapter= new ProfileAcademicProjectsAdapter(DataSet,DataSet,DataSet);
        projectsRecyclerView.setAdapter(adapter);

    }

}
