package in.nfly.dell.nflydemo.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;

public class ProfileAcademicTrainingsAdapter extends RecyclerView.Adapter<ProfileAcademicTrainingsAdapter.ProfileAcademicTrainingsHolder> {

    private ArrayList<String> courseDataSet,certifiedByDataSet,durationDataSet,detailsDataSet;


    public ProfileAcademicTrainingsAdapter(ArrayList<String> courseDataSet, ArrayList<String> certifiedByDataSet, ArrayList<String> durationDataSet, ArrayList<String> detailsDataSet) {
        this.courseDataSet=courseDataSet;
        this.certifiedByDataSet=certifiedByDataSet;
        this.durationDataSet=durationDataSet;
        this.detailsDataSet=detailsDataSet;

    }


    @NonNull
    @Override
    public ProfileAcademicTrainingsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_profile_academic_trainings,parent,false);
        ProfileAcademicTrainingsHolder holder=new ProfileAcademicTrainingsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAcademicTrainingsHolder holder, int position) {
        holder.ProfileAcademicTrainingsCourse.setText(courseDataSet.get(position));
        holder.ProfileAcademicTrainingsDetails.setText(detailsDataSet.get(position));
        holder.ProfileAcademicTrainingsDuration.setText(durationDataSet.get(position));
        holder.ProfileAcademicTrainingsCertifiedBy.setText(certifiedByDataSet.get(position));



    }

    @Override
    public int getItemCount() {
        return courseDataSet.size();
    }

    public class ProfileAcademicTrainingsHolder extends RecyclerView.ViewHolder{

        public TextView ProfileAcademicTrainingsCourse,ProfileAcademicTrainingsDuration,ProfileAcademicTrainingsDetails,
                ProfileAcademicTrainingsCertifiedBy;
        public ProfileAcademicTrainingsHolder(View itemView) {
            super(itemView);

            ProfileAcademicTrainingsCourse=itemView.findViewById(R.id.profileTrainingCourse);
            ProfileAcademicTrainingsDuration=itemView.findViewById(R.id.profileDuration);
            ProfileAcademicTrainingsDetails=itemView.findViewById(R.id.profileDetails);
            ProfileAcademicTrainingsCertifiedBy=itemView.findViewById(R.id.profileCertifiedBy);

        }
    }
}
