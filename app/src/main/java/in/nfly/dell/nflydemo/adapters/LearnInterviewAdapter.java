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
import in.nfly.dell.nflydemo.activities.singleActivities.LearnInterviewSubtopicsActivity;

public class LearnInterviewAdapter extends RecyclerView.Adapter<LearnInterviewAdapter.LearnInterviewHolder> {

    private Context context;
    private ArrayList<String> titleDataSet,imageDataSet,idDataSet;

    public LearnInterviewAdapter(Context context, ArrayList<String> titleDataSet, ArrayList<String> imageDataSet, ArrayList<String> idDataSet) {
        this.context = context;
        this.titleDataSet = titleDataSet;
        this.imageDataSet = imageDataSet;
        this.idDataSet = idDataSet;
    }

    @NonNull
    @Override
    public LearnInterviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_learn_interview,parent,false);
        LearnInterviewHolder holder=new LearnInterviewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearnInterviewHolder holder, final int position) {
        holder.learnInterviewTitle.setText(titleDataSet.get(position));
        holder.learnInterviewImage.setImageResource(Integer.parseInt(imageDataSet.get(0)));

        holder.learnInterviewCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, LearnInterviewSubtopicsActivity.class);
                intent.putExtra("topic_id",idDataSet.get(position));
                intent.putExtra("topic_name",titleDataSet.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titleDataSet.size();
    }

    public class LearnInterviewHolder extends RecyclerView.ViewHolder{

        public CardView learnInterviewCardView;
        public TextView learnInterviewTitle;
        public ImageView learnInterviewImage;
        public LearnInterviewHolder(View itemView) {
            super(itemView);

            learnInterviewCardView=itemView.findViewById(R.id.learnInterviewCardView);
            learnInterviewTitle=itemView.findViewById(R.id.learnInterviewTitle);
            learnInterviewImage=itemView.findViewById(R.id.learnInterviewImage);
        }
    }
}
