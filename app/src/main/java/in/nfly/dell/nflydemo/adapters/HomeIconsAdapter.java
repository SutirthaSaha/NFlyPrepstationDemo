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

public class HomeIconsAdapter extends RecyclerView.Adapter<HomeIconsAdapter.HomeIconsHolder> {

    private Context context;
    private ArrayList<String> titleDataSet;
    private ArrayList<Integer> imageDataSet;

    public HomeIconsAdapter(Context context, ArrayList<String> titleDataSet, ArrayList<Integer> imageDataSet) {
        this.context = context;
        this.titleDataSet = titleDataSet;
        this.imageDataSet = imageDataSet;
    }

    @NonNull
    @Override
    public HomeIconsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_home_icon,parent,false);
        HomeIconsHolder holder=new HomeIconsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeIconsHolder holder, int position) {
        holder.HomeIconsTitle.setText(titleDataSet.get(position));
        Picasso.with(context).load(imageDataSet.get(position)).into(holder.HomeIconsImage);
    }

    @Override
    public int getItemCount() {
        return imageDataSet.size();
    }

    public class HomeIconsHolder extends RecyclerView.ViewHolder{

        public TextView HomeIconsTitle;
        public ImageView HomeIconsImage;
        public HomeIconsHolder(View itemView) {
            super(itemView);

            HomeIconsTitle=itemView.findViewById(R.id.homeIconTitle);
            HomeIconsImage=itemView.findViewById(R.id.homeIconImage);
        }
    }
}
