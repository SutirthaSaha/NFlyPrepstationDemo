package in.nfly.dell.nflydemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;

public class HomeFeaturesIconsAdapter extends RecyclerView.Adapter<HomeFeaturesIconsAdapter.HomeFeaturesIconsHolder> {

    private Context context;
    private ArrayList<String> titleDataSet;
    private ArrayList<Integer> imageDataSet;

    public HomeFeaturesIconsAdapter(Context context, ArrayList<String> titleDataSet, ArrayList<Integer> imageDataSet) {
        this.context = context;
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
    public void onBindViewHolder(@NonNull HomeFeaturesIconsHolder holder, final int position) {
        holder.HomeFeaturesIconsTitle.setText(titleDataSet.get(position));
        Picasso.with(context).load(imageDataSet.get(position)).into(holder.HomeFeaturesIconsImage);
        //holder.HomeFeaturesIconsImage.setImageResource(imageDataSet.get(position));
        holder.homeFeaturesIconsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return imageDataSet.size();
    }

    public class HomeFeaturesIconsHolder extends RecyclerView.ViewHolder{

        public TextView HomeFeaturesIconsTitle;
        public LinearLayout homeFeaturesIconsLayout;
        public ImageView HomeFeaturesIconsImage;
        public HomeFeaturesIconsHolder(View itemView) {
            super(itemView);

            homeFeaturesIconsLayout=itemView.findViewById(R.id.homeFeaturesIconsLayout);
            HomeFeaturesIconsTitle=itemView.findViewById(R.id.homeFeaturesIconTitle);
            HomeFeaturesIconsImage=itemView.findViewById(R.id.homeFeaturesIconImage);
        }
    }
}
