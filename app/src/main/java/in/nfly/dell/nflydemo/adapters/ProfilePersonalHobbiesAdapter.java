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

public class ProfilePersonalHobbiesAdapter extends RecyclerView.Adapter<ProfilePersonalHobbiesAdapter.ProfilePersonalHobbiesHolder> {

    private ArrayList<String> titleDataSet;

    public ProfilePersonalHobbiesAdapter(ArrayList<String> titleDataSet) {
        this.titleDataSet = titleDataSet;
    }

    @NonNull
    @Override
    public ProfilePersonalHobbiesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_profile_lists,parent,false);
        ProfilePersonalHobbiesHolder holder=new ProfilePersonalHobbiesHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfilePersonalHobbiesHolder holder, int position) {
        holder.ProfilePersonalHobbiesTitle.setText(titleDataSet.get(position));

    }

    @Override
    public int getItemCount() {
        return titleDataSet.size();
    }

    public class ProfilePersonalHobbiesHolder extends RecyclerView.ViewHolder{

        public TextView ProfilePersonalHobbiesTitle;
        public ProfilePersonalHobbiesHolder(View itemView) {
            super(itemView);

            ProfilePersonalHobbiesTitle=itemView.findViewById(R.id.profileListItem);

        }
    }
}
