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
import in.nfly.dell.nflydemo.activities.CompanyWisePrepActivity;
import in.nfly.dell.nflydemo.activities.ExamWisePrepActivity;
import in.nfly.dell.nflydemo.activities.TopicWiseActivity;

public class HomePracticeAdapter extends RecyclerView.Adapter<HomePracticeAdapter.HomePracticeHolder>{

    private Context context;
    private ArrayList<String> titleDataSet,subtitleDataSet;
    private ArrayList<Integer> imageDataSet,idDataSet;

    public HomePracticeAdapter(Context context, ArrayList<String> titleDataSet, ArrayList<String> subtitleDataSet, ArrayList<Integer> imageDataSet, ArrayList<Integer> idDataSet) {
        this.context = context;
        this.titleDataSet = titleDataSet;
        this.subtitleDataSet = subtitleDataSet;
        this.imageDataSet = imageDataSet;
        this.idDataSet = idDataSet;
    }

    @NonNull
    @Override
    public HomePracticeAdapter.HomePracticeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_home_prep_hub,parent,false);
        HomePracticeHolder holder=new HomePracticeHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomePracticeAdapter.HomePracticeHolder holder, final int position) {
        holder.HomePracticeTitle.setText(titleDataSet.get(position));
        //holder.HomePracticeImage.setImageResource(imageDataSet.get(position));
        Picasso.with(context).load(imageDataSet.get(position)).into(holder.HomePracticeImage);
        holder.HomePracticeSubTitle.setText(subtitleDataSet.get(position));
        holder.HomePracticeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, Integer.toString(idDataSet.get(position)), Toast.LENGTH_SHORT).show();
                if (position==0){
                    Intent intent=new Intent(context,CompanyWisePrepActivity.class);
                    context.startActivity(intent);
                }else if (position==1){
                    Intent intent=new Intent(context, TopicWiseActivity.class);
                    context.startActivity(intent);
                }else if(position==2){
                    Intent intent=new Intent(context, ExamWisePrepActivity.class);
                    context.startActivity(intent);
                }else{
                    Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageDataSet.size();
    }

    public class HomePracticeHolder extends RecyclerView.ViewHolder{

        public TextView HomePracticeTitle, HomePracticeSubTitle;
        public ImageView HomePracticeImage;
        public CardView HomePracticeCardView;
        public HomePracticeHolder(View itemView) {
            super(itemView);

            HomePracticeCardView=itemView.findViewById(R.id.homePrepHubCardView);
            HomePracticeTitle=itemView.findViewById(R.id.home_prephub_text);
            HomePracticeSubTitle=itemView.findViewById(R.id.home_prephub_sub_text);
            HomePracticeImage=itemView.findViewById(R.id.home_prephub_image);
        }
    }
}
