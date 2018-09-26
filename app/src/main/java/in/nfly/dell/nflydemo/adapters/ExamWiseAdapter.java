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
import in.nfly.dell.nflydemo.activities.ExamWisePrepActivity;
import in.nfly.dell.nflydemo.activities.singleActivities.ExamWiseDetailsActivity;

public class ExamWiseAdapter extends RecyclerView.Adapter<ExamWiseAdapter.ExamWiseHolder>{
    private ArrayList<String> imageDataSet,titleDataSet,idDataSet,testsDataSet;
    private Context context;

    public ExamWiseAdapter(ArrayList<String> imageDataSet, ArrayList<String> titleDataSet, ArrayList<String> idDataSet, ArrayList<String> testsDataSet, Context context) {
        this.imageDataSet = imageDataSet;
        this.titleDataSet = titleDataSet;
        this.idDataSet = idDataSet;
        this.testsDataSet = testsDataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public ExamWiseAdapter.ExamWiseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_knowledge_base_company_wise,parent,false);
        ExamWiseAdapter.ExamWiseHolder holder=new ExamWiseAdapter.ExamWiseHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExamWiseAdapter.ExamWiseHolder holder, final int position) {
        holder.ExamWiseCardTitle.setText(titleDataSet.get(position));
        //Picasso.with(context).load(imageDataSet.get(position)).into(holder.ExamWiseCardImage);
        holder.ExamWiseCardtests.setText(testsDataSet.get(position));
        Picasso.with(context).load(R.drawable.colored_placementpapers).into(holder.ExamWiseCardImage);
        holder.ExamWiseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ExamWiseDetailsActivity.class);
                intent.putExtra("exam_id",idDataSet.get(position));
                intent.putExtra("exam_name",titleDataSet.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return titleDataSet.size();
    }

    public class ExamWiseHolder extends RecyclerView.ViewHolder{

        public TextView ExamWiseCardTitle,ExamWiseCardtests;
        public ImageView ExamWiseCardImage;
        public CardView ExamWiseCard;
        public ExamWiseHolder(View itemView) {
            super(itemView);
            ExamWiseCardImage=itemView.findViewById(R.id.knowBaseCompanyWiseCardImage);
            ExamWiseCardtests=itemView.findViewById(R.id.knowBaseCompanyWiseCardSector);
            ExamWiseCardTitle=itemView.findViewById(R.id.knowBaseCompanyWiseCardTitle);
            ExamWiseCard=itemView.findViewById(R.id.knowBaseCompanyWiseCard);
        }
    }
}
