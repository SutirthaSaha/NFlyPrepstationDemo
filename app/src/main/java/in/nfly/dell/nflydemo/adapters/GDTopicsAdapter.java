package in.nfly.dell.nflydemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;

public class GDTopicsAdapter extends RecyclerView.Adapter<GDTopicsAdapter.GDTopicsHolder>{

    private Context context;
    private ArrayList<String> titleDataSet;

    public GDTopicsAdapter(Context context, ArrayList<String> titleDataSet) {
        this.context = context;
        this.titleDataSet = titleDataSet;
    }

    @NonNull
    @Override
    public GDTopicsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_learn_gd_topics,parent,false);
        GDTopicsHolder holder=new GDTopicsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GDTopicsHolder holder, int position) {
        holder.GDTopicsCardText.setText(titleDataSet.get(position));
        holder.GDTopicsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return titleDataSet.size();
    }

    public class GDTopicsHolder extends RecyclerView.ViewHolder{

        public CardView GDTopicsCardView;
        public TextView GDTopicsCardText;
        public GDTopicsHolder(View itemView) {
            super(itemView);
            GDTopicsCardView=itemView.findViewById(R.id.GDTopicsCardView);
            GDTopicsCardText=itemView.findViewById(R.id.GDTopicsCardText);
        }
    }
}
