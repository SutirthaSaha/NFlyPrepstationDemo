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
import android.widget.Toast;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.activities.singleActivities.PracticeTestActivity;
import in.nfly.dell.nflydemo.activities.singleActivities.PracticeTestInstructionsActivity;

public class PracticePaperDetailsAdapter extends RecyclerView.Adapter<PracticePaperDetailsAdapter.PracticePaperDetailsHolder>{

    private Context context;
    private ArrayList<String> titleDataSet,idDataSet,numQuesDataSet,durationDataSet;

    public PracticePaperDetailsAdapter(Context context, ArrayList<String> titleDataSet, ArrayList<String> idDataSet, ArrayList<String> numQuesDataSet, ArrayList<String> durationDataSet) {
        this.context = context;
        this.titleDataSet = titleDataSet;
        this.idDataSet = idDataSet;
        this.numQuesDataSet = numQuesDataSet;
        this.durationDataSet = durationDataSet;
    }

    @NonNull
    @Override
    public PracticePaperDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_practice_papers_details,parent,false);
        PracticePaperDetailsHolder holder=new PracticePaperDetailsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PracticePaperDetailsHolder holder, final int position) {

        holder.practicePaperDetailsCardText.setText(titleDataSet.get(position));
        holder.practicePaperDetailsCardNumQues.setText(numQuesDataSet.get(position));
        holder.practicePaperDetailsCardDuration.setText(durationDataSet.get(position));

        holder.practicePaperDetailsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PracticeTestInstructionsActivity.class);
                intent.putExtra("test_id",idDataSet.get(position));
                intent.putExtra("test_name",titleDataSet.get(position));
                intent.putExtra("num_ques",numQuesDataSet.get(position));
                intent.putExtra("test_duration",durationDataSet.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titleDataSet.size();
    }

    public class PracticePaperDetailsHolder extends RecyclerView.ViewHolder{

        public CardView practicePaperDetailsCardView;
        public TextView practicePaperDetailsCardText,practicePaperDetailsCardNumQues,practicePaperDetailsCardDuration;
        public PracticePaperDetailsHolder(View itemView) {
            super(itemView);
            practicePaperDetailsCardView=itemView.findViewById(R.id.practicePaperDetailsCardView);
            practicePaperDetailsCardText=itemView.findViewById(R.id.practicePaperDetailsCardText);
            practicePaperDetailsCardNumQues=itemView.findViewById(R.id.practicePaperDetailsCardNumQues);
            practicePaperDetailsCardDuration=itemView.findViewById(R.id.practicePaperDetailsCardDuration);
        }
    }
}
