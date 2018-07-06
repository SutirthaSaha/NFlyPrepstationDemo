package in.nfly.dell.nflydemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.activities.singleActivities.LearnGDForAgainstActivity;

public class GDTopicsAdapter extends RecyclerView.Adapter<GDTopicsAdapter.GDTopicsHolder>{

    private Context context;
    private ArrayList<String> titleDataSet,forLogicDataSet,againstLogicDataSet;

    public GDTopicsAdapter(Context context, ArrayList<String> titleDataSet, ArrayList<String> forLogicDataSet, ArrayList<String> againstLogicDataSet) {
        this.context = context;
        this.titleDataSet = titleDataSet;
        this.forLogicDataSet = forLogicDataSet;
        this.againstLogicDataSet = againstLogicDataSet;
    }

    @NonNull
    @Override
    public GDTopicsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_learn_gd_topics,parent,false);
        GDTopicsHolder holder=new GDTopicsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GDTopicsHolder holder, final int position) {
        holder.GDTopicsLayoutText.setText(titleDataSet.get(position));
        holder.GDTopicsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, LearnGDForAgainstActivity.class);
                intent.putExtra("for_logic",forLogicDataSet.get(position));
                intent.putExtra("against_logic",againstLogicDataSet.get(position));
                intent.putExtra("topic_name",titleDataSet.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titleDataSet.size();
    }

    public class GDTopicsHolder extends RecyclerView.ViewHolder{

        public LinearLayout GDTopicsLayout;
        public TextView GDTopicsLayoutText;
        public GDTopicsHolder(View itemView) {
            super(itemView);
            GDTopicsLayout=itemView.findViewById(R.id.GDTopicsLayout);
            GDTopicsLayoutText=itemView.findViewById(R.id.GDTopicsLayoutText);
        }
    }
}
