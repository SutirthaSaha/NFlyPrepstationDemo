package in.nfly.dell.nflydemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;

public class CompanySolvedPapersAdapter extends RecyclerView.Adapter<CompanySolvedPapersAdapter.CompanySolvedPapersHolder> {

    private Context context;
    private ArrayList<String> titleDataSet,statusDataSet,urlDataSet,idDataSet;

    public CompanySolvedPapersAdapter(Context context, ArrayList<String> titleDataSet, ArrayList<String> statusDataSet, ArrayList<String> urlDataSet, ArrayList<String> idDataSet) {
        this.context = context;
        this.titleDataSet = titleDataSet;
        this.statusDataSet = statusDataSet;
        this.urlDataSet = urlDataSet;
        this.idDataSet = idDataSet;
    }

    @NonNull
    @Override
    public CompanySolvedPapersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_company_solved_papers,parent,false);
        CompanySolvedPapersHolder holder=new CompanySolvedPapersHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CompanySolvedPapersHolder holder, final int position) {
        holder.CompanySolvedPapersTitle.setText(titleDataSet.get(position));
        if(statusDataSet.get(position).equals("0")) {
            holder.CompanySolvedPapersStatus.setText("UnAvailable");
            holder.CompanySolvedPapersStatus.setTextColor(context.getResources().getColor(android.R.color.holo_red_dark));
        }
        else{
            holder.CompanySolvedPapersStatus.setText("Available");
            holder.CompanySolvedPapersStatus.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }
        //holder.CompanySolvedPapersDate.setText("Available From: "+dateDataSet.get(position));
        holder.CompanySolvedPapaersCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, idDataSet.get(position), Toast.LENGTH_SHORT).show();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.wbut.ac.in/syllabus/CSE_Final_Upto_4h_Year%20Syllabus_14.03.14.pdf"));
                //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlDataSet.get(position)));
                context.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return statusDataSet.size();
    }

    public class CompanySolvedPapersHolder extends RecyclerView.ViewHolder{

        public TextView CompanySolvedPapersTitle, CompanySolvedPapersStatus,CompanySolvedPapersDate;
        public CardView CompanySolvedPapaersCardView;

        public CompanySolvedPapersHolder(View itemView) {
            super(itemView);
            //CompanySolvedPapersDate=itemView.findViewById(R.id.companySolvedPapersAvailability);
            CompanySolvedPapersTitle=itemView.findViewById(R.id.companySolvedPapersTitle);
            CompanySolvedPapersStatus=itemView.findViewById(R.id.companySolvedPapersStatus);
            CompanySolvedPapaersCardView=itemView.findViewById(R.id.companySolvedPapersCardView);
        }
    }
}

