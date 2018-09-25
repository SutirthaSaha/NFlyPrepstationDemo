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
import in.nfly.dell.nflydemo.activities.NoInternetActivity;
import in.nfly.dell.nflydemo.activities.singleActivities.TopicWiseTestActivity;

public class TopicWiseAdapter extends RecyclerView.Adapter<TopicWiseAdapter.TopicWiseHolder>{
    private Context context;
    private ArrayList<String> titleDataSet,imageDataSet,idDataSet;

    public TopicWiseAdapter(Context context, ArrayList<String> titleDataSet, ArrayList<String> imageDataSet, ArrayList<String> idDataSet) {
        this.context = context;
        this.titleDataSet = titleDataSet;
        this.imageDataSet = imageDataSet;
        this.idDataSet = idDataSet;
    }

    @NonNull
    @Override
    public TopicWiseAdapter.TopicWiseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_learn_interview,parent,false);
        TopicWiseAdapter.TopicWiseHolder holder=new TopicWiseAdapter.TopicWiseHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopicWiseAdapter.TopicWiseHolder holder, final int position) {
        holder.topicWiseTitle.setText(titleDataSet.get(position));
        //Picasso.with(context).load(imageDataSet.get(position)).into(holder.topicWiseImage);
        Picasso.with(context).load(R.drawable.ic_computer_white).into(holder.topicWiseImage);
        //  holder.learnInterviewImage.setImageResource(Integer.parseInt(imageDataSet.get(0)));
        holder.topicWiseCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, TopicWiseTestActivity.class);
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


    public class TopicWiseHolder extends RecyclerView.ViewHolder{

        public CardView topicWiseCardView;
        public TextView topicWiseTitle;
        public ImageView topicWiseImage;
        public TopicWiseHolder(View itemView) {
            super(itemView);

            topicWiseTitle=itemView.findViewById(R.id.learnInterviewTitle);
            topicWiseImage=itemView.findViewById(R.id.learnInterviewImage);
            topicWiseCardView=itemView.findViewById(R.id.learnInterviewCardView);
        }
    }
}
