package in.nfly.dell.nflydemo.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;

public class ProfileAcademicProjectsAdapter extends RecyclerView.Adapter<ProfileAcademicProjectsAdapter.ProfileAcademicProjectsHolder> {

    private ArrayList<String> projectTitleDataSet,projectLinkDataSet,descriptionDataSet;


    public ProfileAcademicProjectsAdapter(ArrayList<String> projectTitleDataSet, ArrayList<String> projectLinkDataSet, ArrayList<String> descriptionDataSet) {
        this.projectLinkDataSet=projectLinkDataSet;
        this.projectTitleDataSet=projectTitleDataSet;
        this.descriptionDataSet=descriptionDataSet;


    }


    @NonNull
    @Override
    public ProfileAcademicProjectsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_profile_academic_projects,parent,false);
        ProfileAcademicProjectsHolder holder=new ProfileAcademicProjectsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAcademicProjectsHolder holder, int position) {
        holder.ProfileAcademicProjectsTitle.setText(projectTitleDataSet.get(position));
        holder.ProfileAcademicProjectsLink.setText(projectLinkDataSet.get(position));
        holder.ProfileAcademicProjectsDescription.setText(descriptionDataSet.get(position));




    }

    @Override
    public int getItemCount() {
        return projectTitleDataSet.size();
    }

    public class ProfileAcademicProjectsHolder extends RecyclerView.ViewHolder{

        public TextView ProfileAcademicProjectsTitle,ProfileAcademicProjectsLink,ProfileAcademicProjectsDescription;
        public ProfileAcademicProjectsHolder(View itemView) {
            super(itemView);

            ProfileAcademicProjectsTitle=itemView.findViewById(R.id.profileProjectTitle);
            ProfileAcademicProjectsDescription=itemView.findViewById(R.id.profileDescription);
            ProfileAcademicProjectsLink=itemView.findViewById(R.id.profileProjectLink);


        }
    }
}
