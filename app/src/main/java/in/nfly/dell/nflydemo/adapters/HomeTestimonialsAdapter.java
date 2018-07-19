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

public class HomeTestimonialsAdapter extends RecyclerView.Adapter<HomeTestimonialsAdapter.HomeTestimonialsHolder> {

    private ArrayList<String> testimonialDataSet,nameDataSet;


    public HomeTestimonialsAdapter(ArrayList<String> titleDataSet,ArrayList<String> subtitleDataSet) {
        this.testimonialDataSet = titleDataSet;
        this.nameDataSet=subtitleDataSet;

    }

    @NonNull
    @Override
    public HomeTestimonialsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_home_testimonials,parent,false);
        HomeTestimonialsHolder holder=new HomeTestimonialsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeTestimonialsHolder holder, int position) {
        holder.HomeTestimonialsText.setText(testimonialDataSet.get(position));
        holder.HomeTestimonialsName.setText(nameDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return nameDataSet.size();
    }

    public class HomeTestimonialsHolder extends RecyclerView.ViewHolder{

        public TextView HomeTestimonialsName, HomeTestimonialsText;
        public HomeTestimonialsHolder(View itemView) {
            super(itemView);

            HomeTestimonialsText=itemView.findViewById(R.id.homeTestimonialsText);
            HomeTestimonialsName=itemView.findViewById(R.id.homeTestimonialsName);

        }
    }
}
