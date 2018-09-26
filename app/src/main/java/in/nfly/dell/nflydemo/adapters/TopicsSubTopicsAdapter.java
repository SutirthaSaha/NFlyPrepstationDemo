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
import in.nfly.dell.nflydemo.activities.singleActivities.TopicWiseTestActivity;
public class TopicsSubTopicsAdapter extends RecyclerView.Adapter<TopicsSubTopicsAdapter.TopicsSubTopicsHolder>{
    private ArrayList<String> imageDataSet,titleDataSet,idDataSet,questionsDataSet;
    private Context context;

    public TopicsSubTopicsAdapter(ArrayList<String> imageDataSet, ArrayList<String> titleDataSet, ArrayList<String> idDataSet, ArrayList<String> questionsDataSet, Context context) {
        this.imageDataSet = imageDataSet;
        this.titleDataSet = titleDataSet;
        this.idDataSet = idDataSet;
        this.questionsDataSet = questionsDataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public TopicsSubTopicsAdapter.TopicsSubTopicsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_knowledge_base_company_wise,parent,false);
        TopicsSubTopicsAdapter.TopicsSubTopicsHolder holder=new TopicsSubTopicsAdapter.TopicsSubTopicsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopicsSubTopicsAdapter.TopicsSubTopicsHolder holder, final int position) {
        holder.TopicsSubTopicsCardTitle.setText(titleDataSet.get(position));
        //Picasso.with(context).load(imageDataSet.get(position)).into(holder.TopicsSubTopicsCardImage);
        holder.TopicsSubTopicsCardQuestions.setText(questionsDataSet.get(position));
        Picasso.with(context).load(R.drawable.colored_placementpapers).into(holder.TopicsSubTopicsCardImage);
        holder.TopicsSubTopicsCard.setOnClickListener(new View.OnClickListener() {
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

    public class TopicsSubTopicsHolder extends RecyclerView.ViewHolder{

        public TextView TopicsSubTopicsCardTitle,TopicsSubTopicsCardQuestions;
        public ImageView TopicsSubTopicsCardImage;
        public CardView TopicsSubTopicsCard;
        public TopicsSubTopicsHolder(View itemView) {
            super(itemView);
            TopicsSubTopicsCardImage=itemView.findViewById(R.id.knowBaseCompanyWiseCardImage);
            TopicsSubTopicsCardQuestions=itemView.findViewById(R.id.knowBaseCompanyWiseCardSector);
            TopicsSubTopicsCardTitle=itemView.findViewById(R.id.knowBaseCompanyWiseCardTitle);
            TopicsSubTopicsCard=itemView.findViewById(R.id.knowBaseCompanyWiseCard);
        }
    }
}