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

public class HomeFeaturesIconsAdapter extends RecyclerView.Adapter<HomeFeaturesIconsAdapter.HomeFeaturesIconsHolder> {

    private ArrayList<String> titleDataSet;
    private ArrayList<Integer> imageDataSet;

    public HomeFeaturesIconsAdapter(ArrayList<String> titleDataSet, ArrayList<Integer> imageDataSet) {
        this.titleDataSet = titleDataSet;
        this.imageDataSet = imageDataSet;
    }

    @NonNull
    @Override
    public HomeFeaturesIconsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_home_features_icon,parent,false);
        HomeFeaturesIconsHolder holder=new HomeFeaturesIconsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeFeaturesIconsHolder holder, int position) {
        holder.HomeFeaturesIconsTitle.setText(titleDataSet.get(position));
        holder.HomeFeaturesIconsImage.setImageResource(imageDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return imageDataSet.size();
    }

    public class HomeFeaturesIconsHolder extends RecyclerView.ViewHolder{

        public TextView HomeFeaturesIconsTitle;
        public ImageView HomeFeaturesIconsImage;
        public HomeFeaturesIconsHolder(View itemView) {
            super(itemView);

            HomeFeaturesIconsTitle=itemView.findViewById(R.id.homeFeaturesIconTitle);
            HomeFeaturesIconsImage=itemView.findViewById(R.id.homeFeaturesIconImage);
        }
    }
}
