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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.activities.CourseStudyActivity;
import in.nfly.dell.nflydemo.activities.JobRoleWiseDetailsActivity;
import in.nfly.dell.nflydemo.activities.KnowledgeBaseActivity;

public class HomeCareerAdapter extends RecyclerView.Adapter<HomeCareerAdapter.HomeCareerHolder> {

    private Context context;
    private ArrayList<String> idDataSet, titleDataSet,imageDataSet;

    public HomeCareerAdapter(Context context, ArrayList<String> idDataSet, ArrayList<String> titleDataSet, ArrayList<String> imageDataSet) {
        this.context = context;
        this.idDataSet = idDataSet;
        this.titleDataSet = titleDataSet;
        this.imageDataSet = imageDataSet;
    }

    @NonNull
    @Override
    public HomeCareerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_home_companies,parent,false);
        HomeCareerHolder holder=new HomeCareerHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCareerHolder holder, final int position) {
        holder.HomeCareerTitle.setText(titleDataSet.get(position));
        Picasso.with(context).load(imageDataSet.get(position)).into(holder.HomeCareerImage);
        holder.HomeCareerCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, idDataSet.get(position), Toast.LENGTH_SHORT).show();
                if((position!=titleDataSet.size()-1)){
                    Intent intent=new Intent(context, JobRoleWiseDetailsActivity.class);
                    intent.putExtra("job_role_id",idDataSet.get(position));
                    intent.putExtra("job_role_name",titleDataSet.get(position));
                    context.startActivity(intent);
                }
                else{
                    Intent intent=new Intent(context, KnowledgeBaseActivity.class);
                    intent.putExtra("fragment_no",1);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageDataSet.size();
    }

    public class HomeCareerHolder extends RecyclerView.ViewHolder{

        public CardView HomeCareerCardView;
        public TextView HomeCareerTitle;
        public ImageView HomeCareerImage;
        public HomeCareerHolder(View itemView) {
            super(itemView);

            HomeCareerCardView=itemView.findViewById(R.id.homeCompanyCardView);
            HomeCareerTitle=itemView.findViewById(R.id.homeCompanyTitle);
            HomeCareerImage=itemView.findViewById(R.id.homeCompanyImage);
        }
    }
}
