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
import in.nfly.dell.nflydemo.activities.PracticePaperDetailsActivity;

public class PracticeCompanyWiseAdapter extends RecyclerView.Adapter<PracticeCompanyWiseAdapter.PracticeCompanyWiseHolder>{

    private ArrayList<String> titleDataSet,imageDataSet,numberDataSet,idDataSet;
    private Context context;

    public PracticeCompanyWiseAdapter(ArrayList<String> titleDataSet, ArrayList<String> imageDataSet, ArrayList<String> numberDataSet, ArrayList<String> idDataSet, Context context) {
        this.titleDataSet = titleDataSet;
        this.imageDataSet = imageDataSet;
        this.numberDataSet = numberDataSet;
        this.idDataSet = idDataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public PracticeCompanyWiseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_practice_company_wise,parent,false);
        PracticeCompanyWiseHolder holder=new PracticeCompanyWiseHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PracticeCompanyWiseHolder holder, final int position) {
        holder.practiceCompanyWiseCardTitle.setText(titleDataSet.get(position));
        //Picasso.with(context).load(imageDataSet.get(position)).into(holder.practiceCompanyWiseCardImage);
        //holder.practiceCompanyWiseCardImage.setImageResource(Integer.parseInt(imageDataSet.get(position)));
        //holder.practiceCompanyWiseCardNumber.setText("Papers "+numberDataSet.get(position));
        holder.practiceCompanyWiseCardImage.setImageResource(Integer.parseInt(Integer.toString(R.drawable.ic_computer_black)));
        holder.practiceCompanyWiseCardNumber.setText("Papers : "+"4");
        holder.practiceCompanywiseCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PracticePaperDetailsActivity.class);
                intent.putExtra("subtopic_id",idDataSet.get(position));
                intent.putExtra("subtopic_name",titleDataSet.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titleDataSet.size();
    }

    public class PracticeCompanyWiseHolder extends RecyclerView.ViewHolder{

        public CardView practiceCompanywiseCardView;
        public TextView practiceCompanyWiseCardTitle,practiceCompanyWiseCardNumber;
        public ImageView practiceCompanyWiseCardImage;
        public PracticeCompanyWiseHolder(View itemView) {
            super(itemView);
            practiceCompanywiseCardView=itemView.findViewById(R.id.practiceCompanyWiseCardView);
            practiceCompanyWiseCardImage=itemView.findViewById(R.id.practiceCompanyWiseCardImage);
            practiceCompanyWiseCardTitle=itemView.findViewById(R.id.practiceCompanyWiseCardTitle);
            practiceCompanyWiseCardNumber=itemView.findViewById(R.id.practiceCompanyWiseCardNumber);
        }
    }
}
