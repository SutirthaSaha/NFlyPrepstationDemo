package in.nfly.dell.nflydemo.adapters;

import android.content.Context;
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

public class LearnTipsAdapter extends RecyclerView.Adapter<LearnTipsAdapter.LearnTipsHolder>{

    private Context context;
    private ArrayList<String> titleDataSet,imageDataSet;

    public LearnTipsAdapter(Context context, ArrayList<String> titleDataSet, ArrayList<String> imageDataSet) {
        this.context = context;
        this.titleDataSet = titleDataSet;
        this.imageDataSet = imageDataSet;
    }

    @NonNull
    @Override
    public LearnTipsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_learn_interview,parent,false);
        LearnTipsHolder holder=new LearnTipsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearnTipsHolder holder, int position) {
        holder.learnInterviewTitle.setText(titleDataSet.get(position));
        holder.learnInterviewImage.setImageResource(Integer.parseInt(imageDataSet.get(position)));
    }

    @Override
    public int getItemCount() {
        return imageDataSet.size();
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
