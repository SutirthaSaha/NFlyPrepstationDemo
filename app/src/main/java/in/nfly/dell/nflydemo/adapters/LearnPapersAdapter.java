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

public class LearnPapersAdapter extends RecyclerView.Adapter<LearnPapersAdapter.LearnPapersHolder> {

    private ArrayList<String> titleDataSet,imageDataSet,numberDataSet;
    private Context context;

    public LearnPapersAdapter(ArrayList<String> titleDataSet, ArrayList<String> imageDataSet, ArrayList<String> numberDataSet, Context context) {
        this.titleDataSet = titleDataSet;
        this.imageDataSet = imageDataSet;
        this.numberDataSet = numberDataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public LearnPapersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_learn_papers,parent,false);
        LearnPapersAdapter.LearnPapersHolder holder=new LearnPapersAdapter.LearnPapersHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull LearnPapersHolder holder, int position) {
        Picasso.with(context).load(imageDataSet.get(position)).into(holder.learnPapersCardImage);
        holder.learnPapersCardTitle.setText(titleDataSet.get(position));
        holder.learnPapersCardNumber.setText("Papers : "+numberDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return imageDataSet.size();
    }

    public class LearnPapersHolder extends RecyclerView.ViewHolder{

        public TextView learnPapersCardTitle,learnPapersCardNumber;
        public ImageView learnPapersCardImage;
        public LearnPapersHolder(View itemView) {
            super(itemView);

            learnPapersCardImage=itemView.findViewById(R.id.learnPapersCardImage);
            learnPapersCardTitle=itemView.findViewById(R.id.learnPapersCardTitle);
            learnPapersCardNumber=itemView.findViewById(R.id.learnPapersCardNumber);

        }
    }
}
