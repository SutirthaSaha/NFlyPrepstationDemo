package in.nfly.dell.nflydemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.activities.singleActivities.CompanyMockTestDetailsActivity;
import in.nfly.dell.nflydemo.activities.singleActivities.PracticeTestInstructionsActivity;

public class CompanyMockTestAdapter extends RecyclerView.Adapter<CompanyMockTestAdapter.CompanyMockTestHolder> {

    private Context context;
    private ArrayList<String> titleDataSet,statusDataSet,dateDataSet,idDataSet;

    public CompanyMockTestAdapter(Context context, ArrayList<String> titleDataSet, ArrayList<String> statusDataSet, ArrayList<String> dateDataSet, ArrayList<String> idDataSet) {
        this.context = context;
        this.titleDataSet = titleDataSet;
        this.statusDataSet = statusDataSet;
        this.dateDataSet = dateDataSet;
        this.idDataSet = idDataSet;
    }

    @NonNull
    @Override
    public CompanyMockTestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_company_mock_tests,parent,false);
        CompanyMockTestHolder holder=new CompanyMockTestHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyMockTestHolder holder, final int position) {
        holder.CompanyMockTestTitle.setText(titleDataSet.get(position));
        if(statusDataSet.get(position).equals("0")) {
            holder.CompanyMockTestStatus.setText("UnAvailable");
            holder.CompanyMockTestStatus.setTextColor(context.getResources().getColor(android.R.color.holo_red_dark));
        }
        else{
            holder.CompanyMockTestStatus.setText("Available");
            holder.CompanyMockTestStatus.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }
        holder.CompanyMockTestDate.setText("Available From: "+dateDataSet.get(position));
        holder.CompanyMockTestCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CompanyMockTestDetailsActivity.class);
                intent.putExtra("test_id",idDataSet.get(position));
                intent.putExtra("test_name",titleDataSet.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return statusDataSet.size();
    }

    public class CompanyMockTestHolder extends RecyclerView.ViewHolder{

        public TextView CompanyMockTestTitle, CompanyMockTestStatus,CompanyMockTestDate;
        public CardView CompanyMockTestCardView;
        public CompanyMockTestHolder(View itemView) {
            super(itemView);

            CompanyMockTestCardView=itemView.findViewById(R.id.companyMockTestCardView);
            CompanyMockTestDate=itemView.findViewById(R.id.companyMockTestAvailability);
            CompanyMockTestTitle=itemView.findViewById(R.id.companyMockTestTitle);
            CompanyMockTestStatus=itemView.findViewById(R.id.companyMockTestStatus);

        }
    }
}
