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
import in.nfly.dell.nflydemo.activities.singleActivities.LearnTipsDetailsActivity;

public class LearnTipsAdapter extends RecyclerView.Adapter<LearnTipsAdapter.LearnTipsHolder>{

    private Context context;
    private ArrayList<String> titleDataSet,imageDataSet,textDataSet,idDataSet,bgImageDataSet;

    public LearnTipsAdapter(Context context, ArrayList<String> titleDataSet, ArrayList<String> imageDataSet, ArrayList<String> textDataSet, ArrayList<String> idDataSet, ArrayList<String> bgImageDataSet) {
        this.context = context;
        this.titleDataSet = titleDataSet;
        this.imageDataSet = imageDataSet;
        this.textDataSet = textDataSet;
        this.idDataSet = idDataSet;
        this.bgImageDataSet = bgImageDataSet;
    }

    @NonNull
    @Override
    public LearnTipsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_learn_interview,parent,false);
        LearnTipsHolder holder=new LearnTipsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearnTipsHolder holder, final int position) {
        holder.learnInterviewTitle.setText(titleDataSet.get(position));
        Picasso.with(context).load(imageDataSet.get(position)).into(holder.learnInterviewImage);
       // holder.learnInterviewImage.setImageResource(Integer.parseInt(imageDataSet.get(0)));
        holder.learnInterviewCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, LearnTipsDetailsActivity.class);
                intent.putExtra("tip_id",idDataSet.get(position));
                intent.putExtra("topic_name",titleDataSet.get(position));
                intent.putExtra("topic_text",textDataSet.get(position));
                intent.putExtra("topic_image",bgImageDataSet.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titleDataSet.size();
    }

    public class LearnTipsHolder extends RecyclerView.ViewHolder{

        public CardView learnInterviewCardView;
        public TextView learnInterviewTitle;
        public ImageView learnInterviewImage;
        public LearnTipsHolder(View itemView) {
            super(itemView);

            learnInterviewCardView=itemView.findViewById(R.id.learnInterviewCardView);
            learnInterviewTitle=itemView.findViewById(R.id.learnInterviewTitle);
            learnInterviewImage=itemView.findViewById(R.id.learnInterviewImage);
        }
    }
}
