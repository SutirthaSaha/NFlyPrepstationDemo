package in.nfly.dell.nflydemo.fragments;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import in.nfly.dell.nflydemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LearnGDAgainstFragment extends Fragment {

    private String against_logic;
    private TextView gdAgainstTextView;
    private LinearLayout noGdAgainstText;
    private ScrollView gdAgainstScroll;

    public LearnGDAgainstFragment() {
        // Required empty public constructor
    }

    public static LearnGDAgainstFragment  newInstance(String against_logic) {
        LearnGDAgainstFragment fragment = new LearnGDAgainstFragment();
        fragment.against_logic=against_logic;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_learn_gdagainst, container, false);
        gdAgainstTextView=view.findViewById(R.id.gdAgainstTextView);
        noGdAgainstText=view.findViewById(R.id.noGdAgainstText);
        gdAgainstScroll=view.findViewById(R.id.gdAgainstScroll);
        if(against_logic.isEmpty()){
            noGdAgainstText.setVisibility(View.VISIBLE);
        }
        else {
            gdAgainstScroll.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                gdAgainstTextView.setText(Html.fromHtml(against_logic, Html.FROM_HTML_MODE_COMPACT));
            } else {
                gdAgainstTextView.setText(Html.fromHtml(against_logic));
            }
        }
        return view;
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

}
