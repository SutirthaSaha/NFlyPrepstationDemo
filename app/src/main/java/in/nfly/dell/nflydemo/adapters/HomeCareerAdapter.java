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

public class HomeCareerAdapter extends RecyclerView.Adapter<HomeCareerAdapter.HomeCareerHolder> {

    private ArrayList<String> titleDataSet;
    private ArrayList<Integer> imageDataSet;

    public HomeCareerAdapter(ArrayList<String> titleDataSet, ArrayList<Integer> imageDataSet) {
        this.titleDataSet = titleDataSet;
        this.imageDataSet = imageDataSet;
    }

    @NonNull
    @Override
    public HomeCareerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_home_companies,parent,false);
        HomeCareerHolder holder=new HomeCareerHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCareerHolder holder, int position) {
        holder.HomeCareerTitle.setText(titleDataSet.get(position));
        holder.HomeCareerImage.setImageResource(imageDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return imageDataSet.size();
    }

    public class HomeCareerHolder extends RecyclerView.ViewHolder{

        public TextView HomeCareerTitle;
        public ImageView HomeCareerImage;
        public HomeCareerHolder(View itemView) {
            super(itemView);

            HomeCareerTitle=itemView.findViewById(R.id.homeCompanyTitle);
            HomeCareerImage=itemView.findViewById(R.id.homeCompanyImage);
        }
    }
}
