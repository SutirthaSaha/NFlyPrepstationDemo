package in.nfly.dell.nflydemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.activities.CourseStudyActivity;
import in.nfly.dell.nflydemo.activities.singleActivities.ResumeViewActivity;

public class ProfileResumeAdapter extends RecyclerView.Adapter<ProfileResumeAdapter.ProfileResumeHolder>{

    private Context context;
    private ArrayList<String> titleDataSet,imageDataSet,linkDataSet;

    public ProfileResumeAdapter(Context context, ArrayList<String> titleDataSet, ArrayList<String> imageDataSet, ArrayList<String> linkDataSet) {
        this.context = context;
        this.titleDataSet = titleDataSet;
        this.imageDataSet = imageDataSet;
        this.linkDataSet = linkDataSet;
    }

    @NonNull
    @Override
    public ProfileResumeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_profile_resume_item,parent,false);
        ProfileResumeHolder holder=new ProfileResumeHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileResumeHolder holder,final int position) {
        holder.ProfileResumeCardTitle.setText(titleDataSet.get(position));
        Picasso.with(context).load(imageDataSet.get(position)).into(holder.ProfileResumeCardImage);
        holder.ProfileResumeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ResumeViewActivity.class);
                intent.putExtra("resume_link",linkDataSet.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titleDataSet.size();
    }


    public class ProfileResumeHolder extends RecyclerView.ViewHolder{

        public CardView ProfileResumeCardView;
        public TextView ProfileResumeCardTitle;
        public ImageView ProfileResumeCardImage;
        public ProfileResumeHolder(View itemView) {
            super(itemView);
            ProfileResumeCardView=itemView.findViewById(R.id.resumeCardView);
            ProfileResumeCardImage=itemView.findViewById(R.id.resumeCardImage);
            ProfileResumeCardTitle=itemView.findViewById(R.id.resumeCardTitle);
        }
    }
}
