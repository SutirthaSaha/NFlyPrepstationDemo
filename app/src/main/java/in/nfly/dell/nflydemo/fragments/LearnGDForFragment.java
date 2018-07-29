package in.nfly.dell.nflydemo.fragments;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.nfly.dell.nflydemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LearnGDForFragment extends Fragment {

    private String for_logic;
    private TextView gdForTextView;

    public LearnGDForFragment() {
        // Required empty public constructor
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        System.gc();
        Runtime.getRuntime().gc();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.gc();
        Runtime.getRuntime().gc();
    }
    public static LearnGDForFragment  newInstance(String for_logic) {
        LearnGDForFragment fragment = new LearnGDForFragment();
        fragment.for_logic=for_logic;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_learn_gdfor, container, false);
        gdForTextView=view.findViewById(R.id.gdForTextView);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            gdForTextView.setText(Html.fromHtml(for_logic,Html.FROM_HTML_MODE_COMPACT));
        }
        else{
            gdForTextView.setText(Html.fromHtml(for_logic));
        }
        return view;
    }

}
