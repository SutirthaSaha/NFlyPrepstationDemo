package in.nfly.dell.nflydemo.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;

public class DashBoardAdapter extends RecyclerView.Adapter<DashBoardAdapter.DashBoardHolder> {

    private Context context;
    private ArrayList<String> titleDataSet,marksDataSet,dateDataSet;
    private ArrayList<Integer> totalMarksDataSet, actualMarksDataSet;

    public DashBoardAdapter(Context context, ArrayList<String> titleDataSet, ArrayList<String> marksDataSet, ArrayList<String> dateDataSet,ArrayList<Integer> totalMarksDataSet,ArrayList<Integer> actualMarksDataSet) {
        this.context = context;
        this.titleDataSet = titleDataSet;
        this.marksDataSet = marksDataSet;
        this.totalMarksDataSet=totalMarksDataSet;
        this.actualMarksDataSet=actualMarksDataSet;

        this.dateDataSet = dateDataSet;
    }

    @NonNull
    @Override
    public DashBoardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_dashboard,parent,false);
        DashBoardHolder holder=new DashBoardHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DashBoardHolder holder, int position) {
        holder.DashboardTitle.setText(titleDataSet.get(position));
        holder.DashboardMarks.setText(marksDataSet.get(position));
        holder.DashboardDate.setText(dateDataSet.get(position));
        holder.DashboardProgressBar.setMax(totalMarksDataSet.get(position));
        holder.DashboardProgressBar.setSecondaryProgress(50);
        holder.DashboardProgressBar.setSecondaryProgress(actualMarksDataSet.get(position));

    }

    @Override
    public int getItemCount() {
        return marksDataSet.size();
    }

    public class DashBoardHolder extends RecyclerView.ViewHolder{

        public TextView DashboardTitle, DashboardMarks,DashboardDate;
        public ProgressBar DashboardProgressBar;
        public DashBoardHolder(View itemView) {
            super(itemView);

            DashboardDate=itemView.findViewById(R.id.dashboardDate);
            DashboardTitle=itemView.findViewById(R.id.dashboardText);
            DashboardMarks=itemView.findViewById(R.id.dashboardMarks);
            DashboardProgressBar=itemView.findViewById(R.id.dashboardProgress);

        }
    }
}
