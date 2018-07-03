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

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.activities.CourseStudyActivity;
import in.nfly.dell.nflydemo.activities.GDTopicsActivity;

public class LearnGDAdapter extends RecyclerView.Adapter<LearnGDAdapter.LearnGDHolder> {
    private Context context;
    private ArrayList<String> titleDataSet,imageDataSet,idDataSet;

    public LearnGDAdapter(Context context, ArrayList<String> titleDataSet, ArrayList<String> imageDataSet, ArrayList<String> idDataSet) {
        this.context = context;
        this.titleDataSet = titleDataSet;
        this.imageDataSet = imageDataSet;
        this.idDataSet = idDataSet;
    }

    @NonNull
    @Override
    public LearnGDHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_learn_interview,parent,false);
        LearnGDHolder holder=new LearnGDHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearnGDHolder holder, final int position) {
        holder.learnInterviewTitle.setText(titleDataSet.get(position));
        holder.learnInterviewImage.setImageResource(Integer.parseInt(imageDataSet.get(position)));
        holder.learnInterviewCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, GDTopicsActivity.class);
                intent.putExtra("section_id",idDataSet.get(position));
                intent.putExtra("section_name",titleDataSet.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageDataSet.size();
    }


    public class LearnGDHolder extends RecyclerView.ViewHolder{

        public CardView learnInterviewCardView;
        public TextView learnInterviewTitle;
        public ImageView learnInterviewImage;
        public LearnGDHolder(View itemView) {
            super(itemView);

            learnInterviewTitle=itemView.findViewById(R.id.learnInterviewTitle);
            learnInterviewImage=itemView.findViewById(R.id.learnInterviewImage);
            learnInterviewCardView=itemView.findViewById(R.id.learnInterviewCardView);
        }
    }
}
