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

public class CompanyTypeWiseAdapter extends RecyclerView.Adapter<CompanyTypeWiseAdapter.CompanyTypeWiseHolder> {
    private ArrayList<String> imageDataSet,titleDataSet,idDataSet,sectorDataSet;
    private Context context;

    public CompanyTypeWiseAdapter(ArrayList<String> imageDataSet, ArrayList<String> titleDataSet, ArrayList<String> idDataSet, ArrayList<String> sectorDataSet, Context context) {
        this.imageDataSet = imageDataSet;
        this.titleDataSet = titleDataSet;
        this.idDataSet = idDataSet;
        this.sectorDataSet = sectorDataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public CompanyTypeWiseAdapter.CompanyTypeWiseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_knowledge_base_company_wise,parent,false);
        CompanyTypeWiseAdapter.CompanyTypeWiseHolder holder=new CompanyTypeWiseAdapter.CompanyTypeWiseHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyTypeWiseAdapter.CompanyTypeWiseHolder holder, final int position) {
        holder.companyTypeWiseCardTitle.setText(titleDataSet.get(position));
        Picasso.with(context).load(imageDataSet.get(position)).into(holder.companyTypeWiseCardImage);
        holder.companyTypeWiseCardSector.setText(sectorDataSet.get(position));
        //Picasso.with(context).load(R.drawable.ic_computer_white).into(holder.companyTypeWiseCardImage);
        holder.companyTypeWiseCard.setOnClickListener(new View.OnClickListener() {
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
        return titleDataSet.size();
    }

    public class CompanyTypeWiseHolder extends RecyclerView.ViewHolder{

        public TextView companyTypeWiseCardTitle,companyTypeWiseCardSector;
        public ImageView companyTypeWiseCardImage;
        public CardView companyTypeWiseCard;
        public CompanyTypeWiseHolder(View itemView) {
            super(itemView);
            companyTypeWiseCardImage=itemView.findViewById(R.id.knowBaseCompanyWiseCardImage);
            companyTypeWiseCardSector=itemView.findViewById(R.id.knowBaseCompanyWiseCardSector);
            companyTypeWiseCardTitle=itemView.findViewById(R.id.knowBaseCompanyWiseCardTitle);
            companyTypeWiseCard=itemView.findViewById(R.id.knowBaseCompanyWiseCard);
        }
    }
}
