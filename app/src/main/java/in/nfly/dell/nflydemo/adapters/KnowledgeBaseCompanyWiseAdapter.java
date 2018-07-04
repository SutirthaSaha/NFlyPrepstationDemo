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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.activities.CompanyWiseDetailsActivity;

public class KnowledgeBaseCompanyWiseAdapter extends RecyclerView.Adapter<KnowledgeBaseCompanyWiseAdapter.KnowledgeBaseCompanyWiseHolder>{

    private ArrayList<String> imageDataSet,titleDataSet,idDataSet;
    private Context context;

    public KnowledgeBaseCompanyWiseAdapter(ArrayList<String> imageDataSet, ArrayList<String> titleDataSet, ArrayList<String> idDataSet, Context context) {
        this.imageDataSet = imageDataSet;
        this.titleDataSet = titleDataSet;
        this.idDataSet = idDataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public KnowledgeBaseCompanyWiseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_knowledge_base_company_wise,parent,false);
        KnowledgeBaseCompanyWiseHolder holder=new KnowledgeBaseCompanyWiseHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull KnowledgeBaseCompanyWiseHolder holder, final int position) {
        holder.knowBaseCompanyWiseCardTitle.setText(titleDataSet.get(position));
        Picasso.with(context).load(imageDataSet.get(position)).into(holder.knowBaseCompanyWiseCardImage);
        holder.knowBaseCompanyWiseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CompanyWiseDetailsActivity.class);
                intent.putExtra("company_id",idDataSet.get(position));
                intent.putExtra("company_name",titleDataSet.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imageDataSet.size();
    }

    public class KnowledgeBaseCompanyWiseHolder extends RecyclerView.ViewHolder{

        public TextView knowBaseCompanyWiseCardTitle;
        public ImageView knowBaseCompanyWiseCardImage;
        public CardView knowBaseCompanyWiseCard;
        public KnowledgeBaseCompanyWiseHolder(View itemView) {
            super(itemView);

            knowBaseCompanyWiseCardImage=itemView.findViewById(R.id.knowBaseCompanyWiseCardImage);
            knowBaseCompanyWiseCardTitle=itemView.findViewById(R.id.knowBaseCompanyWiseCardTitle);
            knowBaseCompanyWiseCard=itemView.findViewById(R.id.knowBaseCompanyWiseCard);
        }
    }
}
