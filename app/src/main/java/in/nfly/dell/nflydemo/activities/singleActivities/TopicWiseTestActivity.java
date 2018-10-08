package in.nfly.dell.nflydemo.activities.singleActivities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.activities.TopicWiseActivity;

public class TopicWiseTestActivity extends AppCompatActivity {

    private String topic_id,topic_name;
    private Toolbar toolbar;

    private String urlGetTest="http://nfly.in/gapi/load_rows_one";

    private HashMap<String,HashMap<String,String>> quizDetails=new HashMap<>();
    private HashMap<String,ArrayList<String>> questionOptionsSet=new HashMap<>();
    private HashMap<String,String> optionDetails=new HashMap<>();
    private ArrayList<String> optionsDataSet=new ArrayList<String>();
    private ArrayList<String> questionDataSet=new ArrayList<String>(){};
    private ArrayList<String> answerDataSet=new ArrayList<String>(){};
    private ArrayList<String> quesIdDataSet=new ArrayList<String>(){};
    private ArrayList<String> explanationDataSet=new ArrayList<String>(){};

    private int[] userOptions;

    private int count=0;
    private TextView topicWiseTestQuestion,topicWiseExplanationText;
    private RadioButton topicWiseTestOption1,topicWiseTestOption2,topicWiseTestOption3,topicWiseTestOption4;
    private RadioGroup topicWiseTestOptions;
    private RadioButton selectRadioBtn;
    private Button topicWiseTestPreviousBtn,topicWiseTestNextBtn,topicWiseTestSubmitBtn;
    private CardView topicWiseExplanationCard;
    //private TextView topicWiseQuestionNumberText;

    private int optionIdSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_wise_test);
        Intent intent=getIntent();
        topic_id=intent.getStringExtra("topic_id");
        topic_name=intent.getStringExtra("topic_name");
        setToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //Toast.makeText(this, topic_id, Toast.LENGTH_SHORT).show();

        topicWiseTestQuestion=findViewById(R.id.topicWiseTestQuestion);
        topicWiseTestOption1=findViewById(R.id.topicWiseTestOption1);
        topicWiseTestOption2=findViewById(R.id.topicWiseTestOption2);
        topicWiseTestOption3=findViewById(R.id.topicWiseTestOption3);
        topicWiseTestOption4=findViewById(R.id.topicWiseTestOption4);
        topicWiseTestOptions=findViewById(R.id.topicWiseTestOptions);
        topicWiseTestPreviousBtn=findViewById(R.id.topicWiseTestPreviousBtn);
        topicWiseTestNextBtn=findViewById(R.id.topicWiseTestNextBtn);
        topicWiseExplanationText=findViewById(R.id.topicWiseExplanationText);
        topicWiseExplanationCard=findViewById(R.id.topicWiseExplanationCard);
        setTestQuestions();
    }

    private void setToolbar() {
        toolbar=findViewById(R.id.topicWiseTestToolbar);
        toolbar.setTitle(topic_name);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
    }
    private void setTestQuestions() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,urlGetTest, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=new JSONArray(response);
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        optionDetails=new HashMap<>();
                        optionsDataSet=new ArrayList<String>();

                        quesIdDataSet.add(arrayObject.getString("question_id"));
                        questionDataSet.add(arrayObject.getString("question"));
                        answerDataSet.add(arrayObject.getString("correct_option"));

                        optionsDataSet.add(arrayObject.getString("option_1"));
                        optionsDataSet.add(arrayObject.getString("option_2"));
                        optionsDataSet.add(arrayObject.getString("option_3"));
                        optionsDataSet.add(arrayObject.getString("option_4"));

                        explanationDataSet.add(arrayObject.getString("explanation"));
                        questionOptionsSet.put(questionDataSet.get(i),optionsDataSet);
                    }

                    //Toast.makeText(TopicWiseTestActivity.this, quesIdDataSet.toString(), Toast.LENGTH_SHORT).show();

                    if (questionDataSet.isEmpty()){
                        Toast.makeText(TopicWiseTestActivity.this, "No Questions There", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(TopicWiseTestActivity.this,TopicWiseActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        topicWiseTestNextBtn.setVisibility(View.VISIBLE);
                        topicWiseTestNextBtn.setEnabled(true);
                        //topicWiseQuestionNumberText.setText((count+1)+" of "+questionDataSet.size());
                        //topicWiseTestQuestion.setText((count+1)+". "+questionDataSet.get(count));
                        topicWiseTestQuestion.setText((count+1)+" of "+questionDataSet.size()+".\n"+questionDataSet.get(count));
                        topicWiseTestOption1.setText(questionOptionsSet.get(questionDataSet.get(count)).get(0));
                        topicWiseTestOption2.setText(questionOptionsSet.get(questionDataSet.get(count)).get(1));
                        topicWiseTestOption3.setText(questionOptionsSet.get(questionDataSet.get(count)).get(2));
                        topicWiseTestOption4.setText(questionOptionsSet.get(questionDataSet.get(count)).get(3));
                        topicWiseExplanationText.setText(explanationDataSet.get(count));

                        userOptions = new int[questionDataSet.size()];
                        for (int i = 0; i < questionDataSet.size(); i++) {
                            userOptions[i] = 0;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TopicWiseTestActivity.this, "Error", Toast.LENGTH_SHORT).show();
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
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "test_id");
                params.put("value",topic_id);
                params.put("table","nfly_practice_questions");
                return params;
            }
        };
        MySingleton.getmInstance(TopicWiseTestActivity.this).addToRequestQueue(stringRequest);
    }


    public void onTestPreviousBtnClick(View view) {
        saveUserOption();
        count--;
        if(count==0){
            topicWiseTestPreviousBtn.setVisibility(View.INVISIBLE);
            topicWiseTestPreviousBtn.setEnabled(false);
        }
        if(count>0){
            topicWiseTestPreviousBtn.setVisibility(View.VISIBLE);
            topicWiseTestPreviousBtn.setEnabled(true);
        }
        topicWiseTestOption1.setTextColor(getResources().getColor(android.R.color.black));
        topicWiseTestOption2.setTextColor(getResources().getColor(android.R.color.black));
        topicWiseTestOption3.setTextColor(getResources().getColor(android.R.color.black));
        topicWiseTestOption4.setTextColor(getResources().getColor(android.R.color.black));
        showUserOption(count);
        topicWiseTestNextBtn.setVisibility(View.VISIBLE);
        topicWiseTestNextBtn.setEnabled(true);
        //topicWiseQuestionNumberText.setText((count+1)+" of "+questionDataSet.size());
        topicWiseTestQuestion.setText((count+1)+" of "+questionDataSet.size()+".\n"+questionDataSet.get(count));
        topicWiseTestOption1.setText(questionOptionsSet.get(questionDataSet.get(count)).get(0));
        topicWiseTestOption2.setText(questionOptionsSet.get(questionDataSet.get(count)).get(1));
        topicWiseTestOption3.setText(questionOptionsSet.get(questionDataSet.get(count)).get(2));
        topicWiseTestOption4.setText(questionOptionsSet.get(questionDataSet.get(count)).get(3));
        topicWiseExplanationText.setText(explanationDataSet.get(count));
        if(userOptions[count]!=0) {
            topicWiseExplanationCard.setVisibility(View.VISIBLE);
        }
    }

    public void onTestNextBtnClick(View view) {
        saveUserOption();
        count++;
        if(count==(questionDataSet.size()-1)){
            topicWiseTestNextBtn.setVisibility(View.INVISIBLE);
            topicWiseTestNextBtn.setEnabled(false);
        }
        if(count<(questionDataSet.size()-1)){
            topicWiseTestNextBtn.setVisibility(View.VISIBLE);
            topicWiseTestNextBtn.setEnabled(true);
        }
        topicWiseTestOption1.setTextColor(getResources().getColor(android.R.color.black));
        topicWiseTestOption2.setTextColor(getResources().getColor(android.R.color.black));
        topicWiseTestOption3.setTextColor(getResources().getColor(android.R.color.black));
        topicWiseTestOption4.setTextColor(getResources().getColor(android.R.color.black));
        showUserOption(count);
        topicWiseTestPreviousBtn.setVisibility(View.VISIBLE);
        topicWiseTestPreviousBtn.setEnabled(true);
        //topicWiseQuestionNumberText.setText((count+1)+" of "+questionDataSet.size());
        //topicWiseTestQuestion.setText((count+1)+". "+questionDataSet.get(count));
        topicWiseTestQuestion.setText((count+1)+" of "+questionDataSet.size()+".\n"+questionDataSet.get(count));
        topicWiseTestOption1.setText(questionOptionsSet.get(questionDataSet.get(count)).get(0));
        topicWiseTestOption2.setText(questionOptionsSet.get(questionDataSet.get(count)).get(1));
        topicWiseTestOption3.setText(questionOptionsSet.get(questionDataSet.get(count)).get(2));
        topicWiseTestOption4.setText(questionOptionsSet.get(questionDataSet.get(count)).get(3));
        topicWiseExplanationText.setText(explanationDataSet.get(count));
        topicWiseExplanationCard.setVisibility(View.INVISIBLE);

    }
    private void showUserOption(int id) {
        int userPrevOption=R.id.topicWiseTestOption1;
        switch(userOptions[id]){
            case 0:
                return;
            case 1:
                userPrevOption=R.id.topicWiseTestOption1;
                topicWiseExplanationText.setVisibility(View.VISIBLE);
                break;
            case 2:
                userPrevOption=R.id.topicWiseTestOption2;
                topicWiseExplanationText.setVisibility(View.VISIBLE);
                break;
            case 3:
                userPrevOption=R.id.topicWiseTestOption3;
                topicWiseExplanationText.setVisibility(View.VISIBLE);
                break;
            case 4:
                userPrevOption=R.id.topicWiseTestOption4;
                topicWiseExplanationText.setVisibility(View.VISIBLE);
                break;
        }
        selectRadioBtn=findViewById(userPrevOption);
        selectRadioBtn.setChecked(true);
        if(userOptions[id]==Integer.parseInt(answerDataSet.get(id))){
            selectRadioBtn.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        else{
            Toast.makeText(this, "Here", Toast.LENGTH_SHORT).show();
            selectRadioBtn.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    private void saveUserOption() {
        optionIdSelected=topicWiseTestOptions.getCheckedRadioButtonId();
        switch(optionIdSelected){
            case R.id.topicWiseTestOption1:
                userOptions[count]=1;
                break;
            case R.id.topicWiseTestOption2:
                userOptions[count]=2;
                break;
            case R.id.topicWiseTestOption3:
                userOptions[count]=3;
                break;
            case R.id.topicWiseTestOption4:
                userOptions[count]=4;
                break;
            case -1:
                userOptions[count]=0;
        }
        topicWiseTestOptions.clearCheck();
    }

    public void onOptionClick(View view) {
        saveUserOption();
        if(userOptions[count]==Integer.parseInt(answerDataSet.get(count))){
            switch(userOptions[count]){
                case 1:
                    topicWiseTestOption1.setTextColor(getResources().getColor(R.color.colorAccent));
                    topicWiseTestOption1.setHighlightColor(getResources().getColor(R.color.colorAccent));
                    break;
                case 2:
                    topicWiseTestOption2.setTextColor(getResources().getColor(R.color.colorAccent));
                    topicWiseTestOption2.setHighlightColor(getResources().getColor(R.color.colorAccent));
                    break;
                case 3:
                    topicWiseTestOption3.setTextColor(getResources().getColor(R.color.colorAccent));
                    break;
                case 4:
                    topicWiseTestOption4.setTextColor(getResources().getColor(R.color.colorAccent));
                    break;
            }
        }
        else{
            switch(Integer.parseInt(answerDataSet.get(count))){
                case 1:
                    topicWiseTestOption1.setTextColor(getResources().getColor(R.color.colorAccent));
                    break;
                case 2:
                    topicWiseTestOption2.setTextColor(getResources().getColor(R.color.colorAccent));
                    break;
                case 3:
                    topicWiseTestOption3.setTextColor(getResources().getColor(R.color.colorAccent));
                    break;
                case 4:
                    topicWiseTestOption4.setTextColor(getResources().getColor(R.color.colorAccent));
                    break;
            }
            switch(userOptions[count]){
                case 1:
                    topicWiseTestOption1.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                    topicWiseTestOption2.setHighlightColor(getResources().getColor(android.R.color.holo_red_dark));
                    break;
                case 2:
                    topicWiseTestOption2.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                    topicWiseTestOption2.setHighlightColor(getResources().getColor(android.R.color.holo_red_dark));
                    break;
                case 3:
                    topicWiseTestOption3.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                    topicWiseTestOption2.setHighlightColor(getResources().getColor(android.R.color.holo_red_dark));
                    break;
                case 4:
                    topicWiseTestOption4.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                    topicWiseTestOption2.setHighlightColor(getResources().getColor(android.R.color.holo_red_dark));
                    break;
            }
        }
        showUserOption(count);
        topicWiseExplanationCard.setVisibility(View.VISIBLE);
    }
}
