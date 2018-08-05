package in.nfly.dell.nflydemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.activities.JobRoleWiseDetailsActivity;

public class KnowledgeBaseJobWiseAdapter extends RecyclerView.Adapter<KnowledgeBaseJobWiseAdapter.KnowledgeBaseJobWiseHolder>{

    private ArrayList<String> imageDataSet,titleDataSet,idDataSet;
    private Context context;

    public KnowledgeBaseJobWiseAdapter(ArrayList<String> imageDataSet, ArrayList<String> titleDataSet, ArrayList<String> idDataSet, Context context) {
        this.imageDataSet = imageDataSet;
        this.titleDataSet = titleDataSet;
        this.idDataSet = idDataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public KnowledgeBaseJobWiseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_knowledge_base_job_wise,parent,false);
        KnowledgeBaseJobWiseHolder holder=new KnowledgeBaseJobWiseHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull KnowledgeBaseJobWiseHolder holder, final int position) {
        holder.knowBaseJobWiseCardTitle.setText(titleDataSet.get(position));
        Picasso.with(context).load(imageDataSet.get(position)).into(holder.knowBaseJobWiseCardImage);

        holder.knowBaseJobWiseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, idDataSet.get(position), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, JobRoleWiseDetailsActivity.class);
                intent.putExtra("job_role_id",idDataSet.get(position));
                intent.putExtra("job_role_name",titleDataSet.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageDataSet.size();
    }

    public class KnowledgeBaseJobWiseHolder extends RecyclerView.ViewHolder{

        public TextView knowBaseJobWiseCardTitle;
        public ImageView knowBaseJobWiseCardImage;
        public CardView knowBaseJobWiseCard;
        public KnowledgeBaseJobWiseHolder(View itemView) {
            super(itemView);

            knowBaseJobWiseCardImage=itemView.findViewById(R.id.knowBaseJobWiseCardImage);
            knowBaseJobWiseCardTitle=itemView.findViewById(R.id.knowBaseJobWiseCardTitle);
            knowBaseJobWiseCard=itemView.findViewById(R.id.knowBaseJobWiseCard);
        }
    }
}
