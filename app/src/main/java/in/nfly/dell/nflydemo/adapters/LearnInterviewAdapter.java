package in.nfly.dell.nflydemo.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;

public class LearnInterviewAdapter extends RecyclerView.Adapter<LearnInterviewAdapter.LearnInterviewHolder> {

    private ArrayList<String> titleDataSet,imageDataSet;

    public LearnInterviewAdapter(ArrayList<String> titleDataSet, ArrayList<String> imageDataSet) {
        this.titleDataSet = titleDataSet;
        this.imageDataSet = imageDataSet;
    }

    @NonNull
    @Override
    public LearnInterviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_learn_interview,parent,false);
        LearnInterviewHolder holder=new LearnInterviewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearnInterviewHolder holder, int position) {
        holder.learnInterviewTitle.setText(titleDataSet.get(position));
        holder.learnInterviewImage.setImageResource(Integer.parseInt(imageDataSet.get(position)));
    }

    @Override
    public int getItemCount() {
        return imageDataSet.size();
    }

    public class LearnInterviewHolder extends RecyclerView.ViewHolder{

        public TextView learnInterviewTitle;
        public ImageView learnInterviewImage;
        public LearnInterviewHolder(View itemView) {
            super(itemView);

            learnInterviewTitle=itemView.findViewById(R.id.learnInterviewTitle);
            learnInterviewImage=itemView.findViewById(R.id.learnInterviewImage);
        }
    }
}
