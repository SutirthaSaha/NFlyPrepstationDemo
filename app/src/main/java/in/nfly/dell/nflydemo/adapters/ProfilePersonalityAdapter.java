package in.nfly.dell.nflydemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;

public class ProfilePersonalityAdapter extends RecyclerView.Adapter<ProfilePersonalityAdapter.ProfilePersonalityHolder>{

    private ArrayList<String> titleDataSet,textDataSet,progressDataSet;
    private Context context;

    public ProfilePersonalityAdapter(ArrayList<String> titleDataSet, ArrayList<String> textDataSet, ArrayList<String> progressDataSet, Context context) {
        this.titleDataSet = titleDataSet;
        this.textDataSet = textDataSet;
        this.progressDataSet = progressDataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public ProfilePersonalityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_profile_personality,parent,false);
        ProfilePersonalityHolder holder=new ProfilePersonalityHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfilePersonalityHolder holder, int position) {
        holder.ProfilePersonalityCardTitle.setText(titleDataSet.get(position));
        holder.ProfilePersonalityCardText.setText(textDataSet.get(position));
        holder.ProfilePersonalityProgressBar.setProgress(Integer.parseInt(progressDataSet.get(position)));
    }

    @Override
    public int getItemCount() {
        return titleDataSet.size();
    }

    public class ProfilePersonalityHolder extends RecyclerView.ViewHolder{

        public TextView ProfilePersonalityCardTitle,ProfilePersonalityCardText;
        public ProgressBar ProfilePersonalityProgressBar;
        public ProfilePersonalityHolder(View itemView) {
            super(itemView);
            ProfilePersonalityCardText=itemView.findViewById(R.id.profilePersonalityCardText);
            ProfilePersonalityCardTitle=itemView.findViewById(R.id.profilePersonalityCardTitle);
            ProfilePersonalityProgressBar=itemView.findViewById(R.id.profilePersonalityCardProgress);
        }
    }
}
