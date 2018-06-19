package in.nfly.dell.nflydemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;

public class LearnCourseAdapter extends RecyclerView.Adapter<LearnCourseAdapter.LearnCourseHolder> {

    private ArrayList<String> imageDataSet,titleDataSet;
    private Context context;

    public LearnCourseAdapter(ArrayList<String> imageDataSet, ArrayList<String> titleDataSet, Context context) {
        this.imageDataSet = imageDataSet;
        this.titleDataSet = titleDataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public LearnCourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_learn_course_item,parent,false);
        LearnCourseAdapter.LearnCourseHolder holder=new LearnCourseAdapter.LearnCourseHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearnCourseHolder holder, int position) {
        holder.learnCourseCardTitle.setText(titleDataSet.get(position));
        Picasso.with(context).load(imageDataSet.get(position)).into(holder.learnCourseCardImage);
    }

    @Override
    public int getItemCount() {
        return imageDataSet.size();
    }

    public class LearnCourseHolder extends RecyclerView.ViewHolder{

        public TextView learnCourseCardTitle;
        public ImageView learnCourseCardImage;
        public LearnCourseHolder(View itemView) {
            super(itemView);

            learnCourseCardImage=itemView.findViewById(R.id.learnCourseCardImage);
            learnCourseCardTitle=itemView.findViewById(R.id.learnCourseCardTitle);
        }
    }
}
