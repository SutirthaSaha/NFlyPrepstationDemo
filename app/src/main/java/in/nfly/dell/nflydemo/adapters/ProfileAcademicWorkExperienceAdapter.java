package in.nfly.dell.nflydemo.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;

public class ProfileAcademicWorkExperienceAdapter extends RecyclerView.Adapter<ProfileAcademicWorkExperienceAdapter.ProfileAcademicWorkExperienceHolder> {

    private ArrayList<String> companyNameDataSet,positionDataSet,jobTypeDataSet,startDateDataSet,lastDateDataSet, jobDescriptionDataSet;


    public ProfileAcademicWorkExperienceAdapter(ArrayList<String> companyNameDataSet, ArrayList<String> positionDataSet, ArrayList<String> jobTypeDataSet, ArrayList<String> startDateDataSet, ArrayList<String> lastDateDataSet, ArrayList<String> jobDescriptionDataSet) {
        this.companyNameDataSet=companyNameDataSet;
        this.positionDataSet=positionDataSet;
        this.jobTypeDataSet=jobTypeDataSet;
        this.startDateDataSet=startDateDataSet;
        this.lastDateDataSet=lastDateDataSet;
        this.jobDescriptionDataSet=jobDescriptionDataSet;
    }


    @NonNull
    @Override
    public ProfileAcademicWorkExperienceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_profile_academic_work_experience,parent,false);
        ProfileAcademicWorkExperienceHolder holder=new ProfileAcademicWorkExperienceHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAcademicWorkExperienceHolder holder, int position) {
        holder.ProfileAcademicWorkExperienceCompanyName.setText(companyNameDataSet.get(position));
        holder.ProfileAcademicWorkExperienceJobType.setText(jobTypeDataSet.get(position));
        holder.ProfileAcademicWorkExperienceJobDescription.setText(jobDescriptionDataSet.get(position));
        holder.ProfileAcademicWorkExperienceStartDate.setText(startDateDataSet.get(position));
        holder.ProfileAcademicWorkExperienceLastDate.setText(lastDateDataSet.get(position));
        holder.ProfileAcademicWorkExperiencePosition.setText(positionDataSet.get(position));



    }

    @Override
    public int getItemCount() {
        return companyNameDataSet.size();
    }

    public class ProfileAcademicWorkExperienceHolder extends RecyclerView.ViewHolder{

        public TextView ProfileAcademicWorkExperienceCompanyName,ProfileAcademicWorkExperiencePosition,ProfileAcademicWorkExperienceJobType,
                ProfileAcademicWorkExperienceStartDate,
                ProfileAcademicWorkExperienceLastDate,ProfileAcademicWorkExperienceJobDescription;
        public ProfileAcademicWorkExperienceHolder(View itemView) {
            super(itemView);

            ProfileAcademicWorkExperienceCompanyName=itemView.findViewById(R.id.profileCompanyName);
            ProfileAcademicWorkExperiencePosition=itemView.findViewById(R.id.profilePosition);
            ProfileAcademicWorkExperienceJobDescription=itemView.findViewById(R.id.profileJobDescription);
            ProfileAcademicWorkExperienceJobType=itemView.findViewById(R.id.profileJobType);
            ProfileAcademicWorkExperienceStartDate=itemView.findViewById(R.id.profileStartDate);
            ProfileAcademicWorkExperienceLastDate=itemView.findViewById(R.id.profileLastDate);

        }
    }
}
