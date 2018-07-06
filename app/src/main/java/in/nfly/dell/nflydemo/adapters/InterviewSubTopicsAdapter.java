package in.nfly.dell.nflydemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.activities.singleActivities.LearnInterviewQAActivity;

public class InterviewSubTopicsAdapter extends RecyclerView.Adapter<InterviewSubTopicsAdapter.InterviewSubTopicsHolder>{

    private Context context;
    private ArrayList<String> titleDataSet,idDataSet;

    public InterviewSubTopicsAdapter(Context context, ArrayList<String> titleDataSet, ArrayList<String> idDataSet) {
        this.context = context;
        this.titleDataSet = titleDataSet;
        this.idDataSet = idDataSet;
    }

    @NonNull
    @Override
    public InterviewSubTopicsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_learn_interview_subtopics,parent,false);
        InterviewSubTopicsHolder holder=new InterviewSubTopicsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull InterviewSubTopicsHolder holder, final int position) {
        holder.InterviewSubTopicsCardText.setText(titleDataSet.get(position));
        holder.InterviewSubTopicsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, idDataSet.get(position), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, LearnInterviewQAActivity.class);
                intent.putExtra("subtopic_id",idDataSet.get(position));
                intent.putExtra("subtopic_name",titleDataSet.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return idDataSet.size();
    }

    public class InterviewSubTopicsHolder extends RecyclerView.ViewHolder{

        public LinearLayout InterviewSubTopicsCardView;
        public TextView InterviewSubTopicsCardText;
        public InterviewSubTopicsHolder(View itemView) {
            super(itemView);
            InterviewSubTopicsCardView=itemView.findViewById(R.id.interviewSubTopicsLayout);
            InterviewSubTopicsCardText=itemView.findViewById(R.id.interviewSubTopicsCardText);
        }
    }
}
