package in.nfly.dell.nflydemo.fragments.ExamWiseFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.nfly.dell.nflydemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExamWiseMockPapersFragment extends Fragment {


    public ExamWiseMockPapersFragment() {
        // Required empty public constructor
    }

    String exam_id,exam_name;
    public static ExamWiseMockPapersFragment newInstance(String exam_id, String exam_name) {
        ExamWiseMockPapersFragment fragment=new ExamWiseMockPapersFragment();
        fragment.exam_id=exam_id;
        fragment.exam_name=exam_name;
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exam_wise_mock_papers, container, false);
    }

}
