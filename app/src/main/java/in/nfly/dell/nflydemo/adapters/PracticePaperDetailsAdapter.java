package in.nfly.dell.nflydemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;

public class PracticePaperDetailsAdapter extends RecyclerView.Adapter<PracticePaperDetailsAdapter.PracticePaperDetailsHolder>{

    private Context context;
    private ArrayList<String> titleDataSet;

    public PracticePaperDetailsAdapter(Context context, ArrayList<String> titleDataSet) {
        this.context = context;
        this.titleDataSet = titleDataSet;
    }

    @NonNull
    @Override
    public PracticePaperDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_practice_papers_details,parent,false);
        PracticePaperDetailsHolder holder=new PracticePaperDetailsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PracticePaperDetailsHolder holder, int position) {
        holder.practicePaperDetailsCardText.setText(titleDataSet.get(position));
        holder.practicePaperDetailsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Kuch bhi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return titleDataSet.size();
    }

    public class PracticePaperDetailsHolder extends RecyclerView.ViewHolder{

        public CardView practicePaperDetailsCardView;
        public TextView practicePaperDetailsCardText;
        public PracticePaperDetailsHolder(View itemView) {
            super(itemView);
            practicePaperDetailsCardView=itemView.findViewById(R.id.practicePaperDetailsCardView);
            practicePaperDetailsCardText=itemView.findViewById(R.id.practicePaperDetailsCardText);

        }
    }
}