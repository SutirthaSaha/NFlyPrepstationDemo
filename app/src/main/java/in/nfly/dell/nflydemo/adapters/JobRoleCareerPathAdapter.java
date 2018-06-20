package in.nfly.dell.nflydemo.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;

public class JobRoleCareerPathAdapter extends RecyclerView.Adapter<JobRoleCareerPathAdapter.JobRoleCareerPathHolder>{

    private Context context;
    private ArrayList<String> titleDataSet,salaryDataSet,timeDataSet;

    public JobRoleCareerPathAdapter(Context context, ArrayList<String> titleDataSet, ArrayList<String> salaryDataSet, ArrayList<String> timeDataSet) {
        this.context = context;
        this.titleDataSet = titleDataSet;
        this.salaryDataSet = salaryDataSet;
        this.timeDataSet = timeDataSet;
    }

    @NonNull
    @Override
    public JobRoleCareerPathHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_job_role_career_path,parent,false);
        JobRoleCareerPathHolder holder=new JobRoleCareerPathHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull JobRoleCareerPathHolder holder, int position) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.careerPathCardTitle.setText(Html.fromHtml(titleDataSet.get(position),Html.FROM_HTML_MODE_COMPACT));
        }
        else{
            holder.careerPathCardTitle.setText(Html.fromHtml(titleDataSet.get(position)));
        }

        holder.careerPathCardSalary.setText(salaryDataSet.get(position));
        holder.careerPathCardTime.setText(timeDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return timeDataSet.size();
    }

    public class JobRoleCareerPathHolder extends RecyclerView.ViewHolder{

        public TextView careerPathCardTitle,careerPathCardSalary,careerPathCardTime;
        public JobRoleCareerPathHolder(View itemView) {
            super(itemView);
            careerPathCardTitle=itemView.findViewById(R.id.careerPathCardTitle);
            careerPathCardSalary=itemView.findViewById(R.id.careerPathCardSalary);
            careerPathCardTime=itemView.findViewById(R.id.careerPathCardTime);
        }
    }
}
