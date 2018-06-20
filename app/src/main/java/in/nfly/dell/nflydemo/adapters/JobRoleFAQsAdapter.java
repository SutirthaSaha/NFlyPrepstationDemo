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

public class JobRoleFAQsAdapter extends RecyclerView.Adapter<JobRoleFAQsAdapter.JobRoleFAQsHolder>{

    private Context context;
    private ArrayList<String> questionDataSet,answerDataSet;

    public JobRoleFAQsAdapter(Context context, ArrayList<String> questionDataSet, ArrayList<String> answerDataSet) {
        this.context = context;
        this.questionDataSet = questionDataSet;
        this.answerDataSet = answerDataSet;
    }

    @NonNull
    @Override
    public JobRoleFAQsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_job_role_faqs,parent,false);
        JobRoleFAQsHolder holder=new JobRoleFAQsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull JobRoleFAQsHolder holder, int position) {
        holder.jobRoleQuestionText.setText(questionDataSet.get(position));
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.jobRoleAnswerText.setText(Html.fromHtml(answerDataSet.get(position),Html.FROM_HTML_MODE_COMPACT));
        }
        else{
            holder.jobRoleAnswerText.setText(Html.fromHtml(answerDataSet.get(position)));
        }
    }

    @Override
    public int getItemCount() {
        return answerDataSet.size();
    }

    public class JobRoleFAQsHolder extends RecyclerView.ViewHolder{

        public TextView jobRoleQuestionText,jobRoleAnswerText;
        public JobRoleFAQsHolder(View itemView) {
            super(itemView);
            jobRoleQuestionText=itemView.findViewById(R.id.jobRoleQuestionText);
            jobRoleAnswerText=itemView.findViewById(R.id.jobRoleAnswerText);
        }
    }
}
