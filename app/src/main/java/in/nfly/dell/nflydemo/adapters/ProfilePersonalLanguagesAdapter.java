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

public class ProfilePersonalLanguagesAdapter extends RecyclerView.Adapter<ProfilePersonalLanguagesAdapter.ProfilePersonalLanguagesHolder> {

    private ArrayList<String> titleDataSet;

    public ProfilePersonalLanguagesAdapter(ArrayList<String> titleDataSet) {
        this.titleDataSet = titleDataSet;
    }

    @NonNull
    @Override
    public ProfilePersonalLanguagesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_profile_lists,parent,false);
        ProfilePersonalLanguagesHolder holder=new ProfilePersonalLanguagesHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfilePersonalLanguagesHolder holder, int position) {
        holder.ProfilePersonalLanguagesTitle.setText(titleDataSet.get(position));

    }

    @Override
    public int getItemCount() {
        return titleDataSet.size();
    }

    public class ProfilePersonalLanguagesHolder extends RecyclerView.ViewHolder{

        public TextView ProfilePersonalLanguagesTitle;
        public ProfilePersonalLanguagesHolder(View itemView) {
            super(itemView);

            ProfilePersonalLanguagesTitle=itemView.findViewById(R.id.profileListItem);

        }
    }
}
