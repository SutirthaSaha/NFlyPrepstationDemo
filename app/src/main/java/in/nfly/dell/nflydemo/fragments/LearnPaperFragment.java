package in.nfly.dell.nflydemo.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.activities.LoginActivity;
import in.nfly.dell.nflydemo.adapters.HomeIconsAdapter;
import in.nfly.dell.nflydemo.adapters.LearnInterviewAdapter;
import in.nfly.dell.nflydemo.adapters.LearnPapersAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class LearnPaperFragment extends Fragment {
    private RecyclerView learnPapersRecyclerView,learnPapersBannerRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView learnPaperimage;

    //private ArrayList<String> titleDataSet=new ArrayList<String>(){{add("HR Questions");add("Software Tools");add("Eng. Topics");add("Company wise");add("Puzzles");add("Miscellaneous");}};
    //private ArrayList<String> imageDataSet=new ArrayList<String>(){{add(Integer.toString(R.drawable.ic_computer_black));add(Integer.toString(R.drawable.ic_computer_black));add(Integer.toString(R.drawable.ic_computer_black));add(Integer.toString(R.drawable.ic_computer_black));add(Integer.toString(R.drawable.ic_computer_black));add(Integer.toString(R.drawable.ic_computer_black));}};
    //private ArrayList<String> numberDataSet=new ArrayList<String>(){{add(Integer.toString(4));add(Integer.toString(4));add(Integer.toString(4));add(Integer.toString(4));add(Integer.toString(4));add(Integer.toString(4));}};

    private String urlPapers="http://nfly.in/gapi/load_all_rows";
    private ArrayList<Integer> bannerImageDataSet=new ArrayList<Integer>(){
        {
            add(R.drawable.colored_company);
            add(R.drawable.coloredtest);
            add(R.drawable.idea);}};

    private ArrayList<String> bannerTitleDataSet=new ArrayList<String>(){
        {
            add("50+ companies");
            add("200+ papers");
            add("Technical and Non Technical");}};

    private ArrayList<String> titleDataSet=new ArrayList<String>(){};
    private ArrayList<String> imageDataSet=new ArrayList<String>(){};
    private ArrayList<String> numberDataSet=new ArrayList<String>(){};
    private ArrayList<String> idDataSet=new ArrayList<String>(){};

    private ProgressDialog progressDialog;
    public LearnPaperFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_learn_paper, container, false);
        learnPapersRecyclerView=v.findViewById(R.id.learnPapersRecyclerView);
        layoutManager=new GridLayoutManager(getContext(),2);
        learnPapersRecyclerView.setLayoutManager(layoutManager);
        learnPaperimage=v.findViewById(R.id.learnPaperImage);
        Picasso.with(getContext()).load("https://avante.biz/wp-content/uploads/Material-Wallpaper/Material-Wallpaper-004.jpg").into(learnPaperimage);
        setValues();
        setBanner(v);
        return v;
    }
    private void setBanner(View view)
    {
        learnPapersBannerRecyclerView=view.findViewById(R.id.learnPapersBannerIconsRecyclerView);
        layoutManager=new GridLayoutManager(getContext(),3);
        learnPapersBannerRecyclerView.setLayoutManager(layoutManager);

        adapter= new HomeIconsAdapter(getContext(),bannerTitleDataSet,bannerImageDataSet);
        learnPapersBannerRecyclerView.setAdapter(adapter);

    }

    private void setValues() {
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlPapers, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        titleDataSet.add(arrayObject.getString("company_name"));
                        idDataSet.add(arrayObject.getString("company_id"));
                        numberDataSet.add(Integer.toString(5));
                        imageDataSet.add("http://nfly.in/assets/images/company/"+arrayObject.getString("company_cover"));
                    }
                    adapter=new LearnPapersAdapter(titleDataSet,imageDataSet,numberDataSet,idDataSet,getContext());
                    learnPapersRecyclerView.setAdapter(adapter);
                    progressDialog.cancel();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                progressDialog.cancel();
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("X-Api-Key", "59671596837f42d974c7e9dcf38d17e8");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "company_id");
                params.put("order", "ASC");
                params.put("table", "company");
                return params;
            }
        };
        MySingleton.getmInstance(getContext()).addToRequestQueue(stringRequest);
    }

}
