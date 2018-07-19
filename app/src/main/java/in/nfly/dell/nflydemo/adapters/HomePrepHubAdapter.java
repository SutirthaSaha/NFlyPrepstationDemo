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

public class HomePrepHubAdapter extends RecyclerView.Adapter<HomePrepHubAdapter.HomePrepHubHolder> {

    private ArrayList<String> titleDataSet,subtitleDataSet;
    private ArrayList<Integer> imageDataSet;

    public HomePrepHubAdapter(ArrayList<String> titleDataSet,ArrayList<String> subtitleDataSet, ArrayList<Integer> imageDataSet) {
        this.titleDataSet = titleDataSet;
        this.subtitleDataSet=subtitleDataSet;
        this.imageDataSet = imageDataSet;
    }

    @NonNull
    @Override
    public HomePrepHubHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_home_prep_hub,parent,false);
        HomePrepHubHolder holder=new HomePrepHubHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomePrepHubHolder holder, int position) {
        holder.HomePrepHubTitle.setText(titleDataSet.get(position));
        holder.HomePrepHubImage.setImageResource(imageDataSet.get(position));
        holder.HomePrepHubSubTitle.setText(subtitleDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return imageDataSet.size();
    }

    public class HomePrepHubHolder extends RecyclerView.ViewHolder{

        public TextView HomePrepHubTitle, HomePrepHubSubTitle;
        public ImageView HomePrepHubImage;
        public HomePrepHubHolder(View itemView) {
            super(itemView);

            HomePrepHubTitle=itemView.findViewById(R.id.home_prephub_text);
            HomePrepHubSubTitle=itemView.findViewById(R.id.home_prephub_sub_text);
            HomePrepHubImage=itemView.findViewById(R.id.home_prephub_image);
        }
    }
}
