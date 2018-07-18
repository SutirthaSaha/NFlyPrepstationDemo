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
import in.nfly.dell.nflydemo.activities.CourseStudyActivity;

public class HomeCourseAdapter extends RecyclerView.Adapter<HomeCourseAdapter.HomeCourseHolder>{
    private Context context;
    private ArrayList<String> idDataSet;
    private ArrayList<String> titleDataSet;
    private ArrayList<String> imageDataSet;

    public HomeCourseAdapter(Context context, ArrayList<String> idDataSet, ArrayList<String> titleDataSet, ArrayList<String> imageDataSet) {
        this.context = context;
        this.idDataSet = idDataSet;
        this.titleDataSet = titleDataSet;
        this.imageDataSet = imageDataSet;
    }

    @NonNull
    @Override
    public HomeCourseAdapter.HomeCourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_home_companies,parent,false);
        HomeCourseAdapter.HomeCourseHolder holder=new HomeCourseAdapter.HomeCourseHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCourseAdapter.HomeCourseHolder holder, final int position) {
        holder.HomeCourseTitle.setText(titleDataSet.get(position));
        Picasso.with(context).load(imageDataSet.get(position)).into(holder.HomeCourseImage);
        holder.HomeCourseCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CourseStudyActivity.class);
                intent.putExtra("course_id",idDataSet.get(position));
                intent.putExtra("course_name",titleDataSet.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageDataSet.size();
    }

    public class HomeCourseHolder extends RecyclerView.ViewHolder{

        public TextView HomeCourseTitle;
        public ImageView HomeCourseImage;
        public CardView HomeCourseCardView;

        public HomeCourseHolder(View itemView) {
            super(itemView);

            HomeCourseCardView=itemView.findViewById(R.id.homeCompanyCardView);
            HomeCourseTitle=itemView.findViewById(R.id.homeCompanyTitle);
            HomeCourseImage=itemView.findViewById(R.id.homeCompanyImage);
        }
    }
}
