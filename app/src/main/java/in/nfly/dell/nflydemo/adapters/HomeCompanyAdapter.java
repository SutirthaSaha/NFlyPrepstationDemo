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

public class HomeCompanyAdapter extends RecyclerView.Adapter<HomeCompanyAdapter.HomeCompanyHolder> {

    private Context context;
    private ArrayList<String> titleDataSet;
    private ArrayList<String> imageDataSet;

    public HomeCompanyAdapter(Context context, ArrayList<String> titleDataSet, ArrayList<String> imageDataSet) {
        this.context = context;
        this.titleDataSet = titleDataSet;
        this.imageDataSet = imageDataSet;
    }

    @NonNull
    @Override
    public HomeCompanyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_home_companies,parent,false);
        HomeCompanyHolder holder=new HomeCompanyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCompanyHolder holder, int position) {
        holder.HomeCompanyTitle.setText(titleDataSet.get(position));
        Picasso.with(context).load(imageDataSet.get(position)).into(holder.HomeCompanyImage);
    }

    @Override
    public int getItemCount() {
        return imageDataSet.size();
    }

    public class HomeCompanyHolder extends RecyclerView.ViewHolder{

        public TextView HomeCompanyTitle;
        public ImageView HomeCompanyImage;
        public HomeCompanyHolder(View itemView) {
            super(itemView);

            HomeCompanyTitle=itemView.findViewById(R.id.homeCompanyTitle);
            HomeCompanyImage=itemView.findViewById(R.id.homeCompanyImage);
        }
    }
}
