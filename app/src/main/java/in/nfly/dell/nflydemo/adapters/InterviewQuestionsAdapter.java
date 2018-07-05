package in.nfly.dell.nflydemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;

public class InterviewQuestionsAdapter extends RecyclerView.Adapter<InterviewQuestionsAdapter.InterviewQuestionsHolder>{

    private Context context;
    private ArrayList<String> questionDataSet,answerDataSet;

    public InterviewQuestionsAdapter(Context context, ArrayList<String> questionDataSet, ArrayList<String> answerDataSet) {
        this.context = context;
        this.questionDataSet = questionDataSet;
        this.answerDataSet = answerDataSet;
    }

    @NonNull
    @Override
    public InterviewQuestionsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_learn_interview_ques_ans,parent,false);
        InterviewQuestionsHolder holder=new InterviewQuestionsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull InterviewQuestionsHolder holder, int position) {
        holder.InterviewQuestionsCardQuestionText.setText(questionDataSet.get(position));
        holder.InterviewQuestionsCardAnswerText.setText(answerDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return questionDataSet.size();
    }

    public class InterviewQuestionsHolder extends RecyclerView.ViewHolder{

        public CardView InterviewQuestionsCardView;
        public TextView InterviewQuestionsCardQuestionText,InterviewQuestionsCardAnswerText;
        public InterviewQuestionsHolder(View itemView) {
            super(itemView);
            InterviewQuestionsCardView=itemView.findViewById(R.id.interviewQuestionsCardView);
            InterviewQuestionsCardQuestionText=itemView.findViewById(R.id.interviewQuestionsCardQuestionText);
            InterviewQuestionsCardAnswerText=itemView.findViewById(R.id.interviewQuestionsCardAnswerText);
        }
    }
}
