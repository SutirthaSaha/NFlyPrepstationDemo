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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.activities.singleActivities.CompanyTypeWiseActivity;


public class CompanyTypesAdapter  extends RecyclerView.Adapter<CompanyTypesAdapter.CompanyTypesHolder> {
    private Context context;
    private ArrayList<String> titleDataSet,imageDataSet,idDataSet;

    public CompanyTypesAdapter(Context context, ArrayList<String> titleDataSet, ArrayList<String> imageDataSet, ArrayList<String> idDataSet) {
        this.context = context;
        this.titleDataSet = titleDataSet;
        this.imageDataSet = imageDataSet;
        this.idDataSet = idDataSet;
    }

    @NonNull
    @Override
    public CompanyTypesAdapter.CompanyTypesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_learn_interview,parent,false);
        CompanyTypesAdapter.CompanyTypesHolder holder=new CompanyTypesAdapter.CompanyTypesHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyTypesAdapter.CompanyTypesHolder holder, final int position) {
        holder.companyTypesTitle.setText(titleDataSet.get(position));
        //Picasso.with(context).load(imageDataSet.get(position)).into(holder.companyTypesImage);
        holder.companyTypesImage.setImageResource(Integer.parseInt(imageDataSet.get(0)));
        holder.companyTypesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CompanyTypeWiseActivity.class);
                intent.putExtra("type_id",idDataSet.get(position));
                intent.putExtra("type_name",titleDataSet.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titleDataSet.size();
    }

    public class CompanyTypesHolder extends RecyclerView.ViewHolder{

        public CardView companyTypesCardView;
        public TextView companyTypesTitle;
        public ImageView companyTypesImage;
        public CompanyTypesHolder(View itemView) {
            super(itemView);

            companyTypesCardView=itemView.findViewById(R.id.learnInterviewCardView);
            companyTypesTitle=itemView.findViewById(R.id.learnInterviewTitle);
            companyTypesImage=itemView.findViewById(R.id.learnInterviewImage);
        }
    }
}
