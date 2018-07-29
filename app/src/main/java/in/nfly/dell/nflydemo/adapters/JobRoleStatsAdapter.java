package in.nfly.dell.nflydemo.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;

public class JobRoleStatsAdapter extends RecyclerView.Adapter<JobRoleStatsAdapter.JobRoleStatsHolder> {

    private Context context;
    private ArrayList<String> titleDataSet,subTitleDataSet;
    private ArrayList<Integer> imageDataSet;

    public JobRoleStatsAdapter(Context context, ArrayList<String> titleDataSet, ArrayList<String> subTitleDataSet, ArrayList<Integer> imageDataSet) {
        this.context = context;
        this.titleDataSet = titleDataSet;
        this.subTitleDataSet = subTitleDataSet;
        this.imageDataSet = imageDataSet;
    }

    @NonNull
    @Override
    public JobRoleStatsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_job_role_stats_icons,parent,false);
        JobRoleStatsHolder holder=new JobRoleStatsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull JobRoleStatsHolder holder, int position) {
        holder.JobRoleStatsTitle.setText(titleDataSet.get(position));
        Picasso.with(context).load(imageDataSet.get(position)).into(holder.JobRoleStatsImage);
        //holder.JobRoleStatsImage.setImageResource(imageDataSet.get(position));
        holder.JobRoleStatsSubTitle.setText(subTitleDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return imageDataSet.size();
    }

    public class JobRoleStatsHolder extends RecyclerView.ViewHolder{

        public TextView JobRoleStatsTitle,JobRoleStatsSubTitle;
        public ImageView JobRoleStatsImage;
        public JobRoleStatsHolder(View itemView) {
            super(itemView);

            JobRoleStatsTitle=itemView.findViewById(R.id.jobRoleStatsIconsTitle);
            JobRoleStatsSubTitle=itemView.findViewById(R.id.jobRoleStatsIconsSubTitle);
            JobRoleStatsImage=itemView.findViewById(R.id.jobRoleStatsIconsImage);
        }
    }
}
