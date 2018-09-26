package in.nfly.dell.nflydemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;

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
    public void onBindViewHolder(@NonNull CompanyMockTestHolder holder, int position) {
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

    }

    @Override
    public int getItemCount() {
        return statusDataSet.size();
    }

    public class CompanyMockTestHolder extends RecyclerView.ViewHolder{

        public TextView CompanyMockTestTitle, CompanyMockTestStatus,CompanyMockTestDate;
        public CompanyMockTestHolder(View itemView) {
            super(itemView);

            CompanyMockTestDate=itemView.findViewById(R.id.companyMockTestAvailability);
            CompanyMockTestTitle=itemView.findViewById(R.id.companyMockTestTitle);
            CompanyMockTestStatus=itemView.findViewById(R.id.companyMockTestStatus);

        }
    }
}
