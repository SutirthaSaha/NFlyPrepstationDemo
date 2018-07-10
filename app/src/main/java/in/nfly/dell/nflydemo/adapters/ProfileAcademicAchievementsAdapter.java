package in.nfly.dell.nflydemo.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;

public class ProfileAcademicAchievementsAdapter extends RecyclerView.Adapter<ProfileAcademicAchievementsAdapter.ProfileAcademicAchievementsHolder> {

    private ArrayList<String> nameDataSet,descriptionDataSet;


    public ProfileAcademicAchievementsAdapter(ArrayList<String> nameDataSet, ArrayList<String> descriptionDataSet) {
        this.nameDataSet=nameDataSet;
        this.descriptionDataSet=descriptionDataSet;


    }


    @NonNull
    @Override
    public ProfileAcademicAchievementsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_profile_academic_achievements,parent,false);
        ProfileAcademicAchievementsHolder holder=new ProfileAcademicAchievementsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAcademicAchievementsHolder holder, int position) {
        holder.ProfileAcademicAchievementsName.setText(nameDataSet.get(position));
        holder.ProfileAcademicAchievementsDescription.setText(descriptionDataSet.get(position));


    }

    @Override
    public int getItemCount() {
        return nameDataSet.size();
    }

    public class ProfileAcademicAchievementsHolder extends RecyclerView.ViewHolder{

        public TextView ProfileAcademicAchievementsName,ProfileAcademicAchievementsDescription;
        public ProfileAcademicAchievementsHolder(View itemView) {
            super(itemView);

            ProfileAcademicAchievementsName=itemView.findViewById(R.id.profileAchievementsName);
            ProfileAcademicAchievementsDescription=itemView.findViewById(R.id.profileAchievementsDescription);



        }
    }
}
