package in.nfly.dell.nflydemo.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;

public class DashBoardAdapter extends RecyclerView.Adapter<DashBoardAdapter.DashBoardHolder> {

    private ArrayList<String> titleDataSet;
    private ArrayList<String> numberDataSet;

    public DashBoardAdapter(ArrayList<String> titleDataSet, ArrayList<String> numberDataSet) {
        this.titleDataSet = titleDataSet;
        this.numberDataSet = numberDataSet;
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
        holder.DashBoardTitle.setText(titleDataSet.get(position));
        holder.DashBoardNumber.setText(numberDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return numberDataSet.size();
    }

    public class DashBoardHolder extends RecyclerView.ViewHolder{

        public TextView DashBoardTitle, DashBoardNumber;
        public DashBoardHolder(View itemView) {
            super(itemView);

            DashBoardTitle=itemView.findViewById(R.id.dashboardText);
            DashBoardNumber=itemView.findViewById(R.id.dashboardNumber);
        }
    }
}
