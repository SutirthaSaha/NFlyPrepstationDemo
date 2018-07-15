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

public class HomeCourseAdapter extends RecyclerView.Adapter<HomeCourseAdapter.HomeCourseHolder>{
    private Context context;
    private ArrayList<String> titleDataSet;
    private ArrayList<String> imageDataSet;

    public HomeCourseAdapter(Context context, ArrayList<String> titleDataSet, ArrayList<String> imageDataSet) {
        this.context = context;
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
    public void onBindViewHolder(@NonNull HomeCourseAdapter.HomeCourseHolder holder, int position) {
        holder.HomeCourseTitle.setText(titleDataSet.get(position));
        Picasso.with(context).load(imageDataSet.get(position)).into(holder.HomeCourseImage);
    }

    @Override
    public int getItemCount() {
        return imageDataSet.size();
    }

    public class HomeCourseHolder extends RecyclerView.ViewHolder{

        public TextView HomeCourseTitle;
        public ImageView HomeCourseImage;
        public HomeCourseHolder(View itemView) {
            super(itemView);

            HomeCourseTitle=itemView.findViewById(R.id.homeCompanyTitle);
            HomeCourseImage=itemView.findViewById(R.id.homeCompanyImage);
        }
    }
}
