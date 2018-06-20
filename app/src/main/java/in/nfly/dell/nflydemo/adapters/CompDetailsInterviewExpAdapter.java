package in.nfly.dell.nflydemo.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;

public class CompDetailsInterviewExpAdapter extends RecyclerView.Adapter<CompDetailsInterviewExpAdapter.CompDetailsInterviewExpHolder>{

    private Context context;
    private ArrayList<String> expDataSet,nameDataSet;
    private String expDetails;

    public CompDetailsInterviewExpAdapter(Context context, ArrayList<String> expDataSet, ArrayList<String> nameDataSet) {
        this.context = context;
        this.expDataSet = expDataSet;
        this.nameDataSet = nameDataSet;
    }

    @NonNull
    @Override
    public CompDetailsInterviewExpHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_comp_details_interview_exp,parent,false);
        CompDetailsInterviewExpHolder holder=new CompDetailsInterviewExpHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CompDetailsInterviewExpHolder holder, int position) {
        expDetails=expDataSet.get(position);
        holder.compDetailsInterviewExpName.setText(nameDataSet.get(position));
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            holder.compDetailsInterviewExpText.setText(Html.fromHtml(expDetails,Html.FROM_HTML_MODE_COMPACT));
        }
        else{
            holder.compDetailsInterviewExpText.setText(Html.fromHtml(expDetails));
        }
    }

    @Override
    public int getItemCount() {
        return nameDataSet.size();
    }

    public class CompDetailsInterviewExpHolder extends RecyclerView.ViewHolder{

        public TextView compDetailsInterviewExpText,compDetailsInterviewExpName;
        public CompDetailsInterviewExpHolder(View itemView) {
            super(itemView);
            compDetailsInterviewExpText=itemView.findViewById(R.id.compDetailsInterviewExpText);
            compDetailsInterviewExpName=itemView.findViewById(R.id.compDetailsInterviewExpName);
        }
    }
}
