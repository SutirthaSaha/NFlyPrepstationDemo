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
import in.nfly.dell.nflydemo.activities.CoursesActivity;
import in.nfly.dell.nflydemo.activities.InterviewGdPrepActivity;
import in.nfly.dell.nflydemo.activities.LearnActivity;

public class HomePrepHubAdapter extends RecyclerView.Adapter<HomePrepHubAdapter.HomePrepHubHolder> {

    private Context context;
    private ArrayList<String> titleDataSet,subtitleDataSet;
    private ArrayList<Integer> imageDataSet,idDataSet;

    public HomePrepHubAdapter(Context context, ArrayList<String> titleDataSet, ArrayList<String> subtitleDataSet, ArrayList<Integer> imageDataSet, ArrayList<Integer> idDataSet) {
        this.context = context;
        this.titleDataSet = titleDataSet;
        this.subtitleDataSet = subtitleDataSet;
        this.imageDataSet = imageDataSet;
        this.idDataSet = idDataSet;
    }

    @NonNull
    @Override
    public HomePrepHubHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_home_prep_hub,parent,false);
        HomePrepHubHolder holder=new HomePrepHubHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomePrepHubHolder holder, final int position) {
        holder.HomePrepHubTitle.setText(titleDataSet.get(position));
        //holder.HomePrepHubImage.setImageResource(imageDataSet.get(position));
        Picasso.with(context).load(imageDataSet.get(position)).into(holder.HomePrepHubImage);
        holder.HomePrepHubSubTitle.setText(subtitleDataSet.get(position));
        holder.HomePrepHubCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, Integer.toString(idDataSet.get(position)), Toast.LENGTH_SHORT).show();
                if(position==0){
                    Intent intent=new Intent(context, CoursesActivity.class);
                    context.startActivity(intent);
                }else if(position==1){
                    Intent intent=new Intent(context, InterviewGdPrepActivity.class);
                    //intent.putExtra("fragment_id",idDataSet.get(position));
                    context.startActivity(intent);
                }else if(position==2){
                    Intent intent=new Intent(context, InterviewGdPrepActivity.class);
                    //intent.putExtra("fragment_id",idDataSet.get(position));
                    context.startActivity(intent);
                } 
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageDataSet.size();
    }

    public class HomePrepHubHolder extends RecyclerView.ViewHolder{

        public TextView HomePrepHubTitle, HomePrepHubSubTitle;
        public ImageView HomePrepHubImage;
        public CardView HomePrepHubCardView;
        public HomePrepHubHolder(View itemView) {
            super(itemView);

            HomePrepHubCardView=itemView.findViewById(R.id.homePrepHubCardView);
            HomePrepHubTitle=itemView.findViewById(R.id.home_prephub_text);
            HomePrepHubSubTitle=itemView.findViewById(R.id.home_prephub_sub_text);
            HomePrepHubImage=itemView.findViewById(R.id.home_prephub_image);
        }
    }
}
