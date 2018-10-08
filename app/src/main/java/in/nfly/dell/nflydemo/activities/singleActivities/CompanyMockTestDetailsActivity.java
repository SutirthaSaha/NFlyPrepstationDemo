package in.nfly.dell.nflydemo.activities.singleActivities;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import in.nfly.dell.nflydemo.MySingleton;
import in.nfly.dell.nflydemo.R;
import in.nfly.dell.nflydemo.User;
import in.nfly.dell.nflydemo.activities.TopicWiseActivity;

public class CompanyMockTestDetailsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private String test_id,test_name,test_duration;
    private String user_id;
    private Integer status;
    private String urlUserGivenTest="http://nfly.in/gapi/data_exists_two";
    private String urlPrevAttemptId="http://nfly.in/gapi/select_max_on_2";
    private String urlInsertAttempt="http://nfly.in/gapi/insert";
    private String urlGetTest="http://nfly.in/gapi/load_rows_one";
    private String urlUpdateTest="http://nfly.in/testapi/update_with_three";

    private String prev_attempt_id;
    private Integer previous_attempt_id,attempt_id;

    private HashMap<String,HashMap<String,String>> quizDetails=new HashMap<>();
    private HashMap<String,ArrayList<String>> questionOptionsSet=new HashMap<>();
    private HashMap<String,String> optionDetails=new HashMap<>();
    private ArrayList<String> optionsDataSet=new ArrayList<String>();
    private ArrayList<String> questionDataSet=new ArrayList<String>(){};
    private ArrayList<String> answerDataSet=new ArrayList<String>(){};
    private ArrayList<String> quesIdDataSet=new ArrayList<String>(){};

    private int[] userOptions;

    private int count=0;
    private TextView companyMockTestDetailsQuestion;
    private RadioButton companyMockTestDetailsOption1,companyMockTestDetailsOption2,companyMockTestDetailsOption3,companyMockTestDetailsOption4;
    private RadioGroup companyMockTestDetailsOptions;
    private RadioButton selectRadioBtn;
    private Button companyMockTestDetailsPreviousBtn,companyMockTestDetailsNextBtn,companyMockTestDetailsSubmitBtn;
    private TextView practiceCountdownTimerText;

    private int optionIdSelected;
    private int user_score,max_score;
    private String date;
    private static final String FORMAT = "%02d:%02d";
    int seconds , minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_mock_test_details);
        Intent intent=getIntent();
        test_id=intent.getStringExtra("test_id");
        test_name=intent.getStringExtra("test_name");
        //test_duration=intent.getStringExtra("test_duration");

        User user=new User(CompanyMockTestDetailsActivity.this);
        user_id=user.getUser_id();

        setToolbar();
        /*
        practiceCountdownTimerText=findViewById(R.id.practiceCountdownTimerText);
        new CountDownTimer(Integer.parseInt(test_duration)*60*1000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

                practiceCountdownTimerText.setText(""+String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                practiceCountdownTimerText.setText("done!");
                saveUserOption();
                //Toast.makeText(CompanyMockTestDetailsActivity.this, Integer.toString(userOptions[count]), Toast.LENGTH_SHORT).show();

                for(int i=0;i<answerDataSet.size();i++){
                    if(userOptions[i]==Integer.parseInt(answerDataSet.get(i))){
                        user_score=user_score+3;
                    }
                }
                //Toast.makeText(CompanyMockTestDetailsActivity.this, user_score+"/"+max_score, Toast.LENGTH_SHORT).show();
                //updateTestTable();
                companyMockTestDetailsNextBtn.setVisibility(View.INVISIBLE);
                companyMockTestDetailsPreviousBtn.setVisibility(View.INVISIBLE);
                companyMockTestDetailsSubmitBtn.setVisibility(View.INVISIBLE);
                for(int i=0;i<quesIdDataSet.size();i++){
                    int marks=0;
                    if(userOptions[i]==Integer.parseInt(answerDataSet.get(i))){
                        marks=3;
                    }
                    insertResponse(quesIdDataSet.get(i),i,marks);
                }
                insertAttempt();
            }
        }.start();
        */
        checkUserGivenTest();

        companyMockTestDetailsQuestion=findViewById(R.id.companyMockTestDetailsQuestion);
        companyMockTestDetailsOption1=findViewById(R.id.companyMockTestDetailsOption1);
        companyMockTestDetailsOption2=findViewById(R.id.companyMockTestDetailsOption2);
        companyMockTestDetailsOption3=findViewById(R.id.companyMockTestDetailsOption3);
        companyMockTestDetailsOption4=findViewById(R.id.companyMockTestDetailsOption4);
        companyMockTestDetailsOptions=findViewById(R.id.companyMockTestDetailsOptions);
        companyMockTestDetailsPreviousBtn=findViewById(R.id.companyMockTestDetailsPreviousBtn);
        companyMockTestDetailsNextBtn=findViewById(R.id.companyMockTestDetailsNextBtn);
        companyMockTestDetailsSubmitBtn=findViewById(R.id.companyMockTestDetailsSubmitBtn);
        companyMockTestDetailsSubmitBtn.setEnabled(false);
        
    }
    private void setToolbar() {
        toolbar=findViewById(R.id.companyMockTestDetailsToolbar);
        toolbar.setTitle(test_name);
        toolbar.setTitleTextColor(Color.WHITE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void checkUserGivenTest(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlUserGivenTest, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(CompanyMockTestDetailsActivity.this, "User Has Given Test Before", Toast.LENGTH_SHORT).show();
                getPrevAttemptId();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(CompanyMockTestDetailsActivity.this, "Waah pehla attempt mubaarak ho", Toast.LENGTH_SHORT).show();
                previous_attempt_id=0;
                attempt_id=1;
                setTestQuestions();
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
                params.put("key1", "user_id");
                params.put("value1", user_id);
                params.put("key2", "test_id");
                params.put("value2", test_id);
                params.put("table", "nfly_test_result");
                return params;
            }
        };
        MySingleton.getmInstance(CompanyMockTestDetailsActivity.this).addToRequestQueue(stringRequest);
    }

    private void getPrevAttemptId() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlPrevAttemptId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    prev_attempt_id = jsonObject.getString("previous_attempt_id");
                    previous_attempt_id = Integer.parseInt(prev_attempt_id);
                    attempt_id = previous_attempt_id + 1;

                    setTestQuestions();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CompanyMockTestDetailsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("X-Api-Key", "59671596837f42d974c7e9dcf38d17e8");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("max_col", "attempt_id");
                params.put("key1", "user_id");
                params.put("value1", user_id);
                params.put("key2", "test_id");
                params.put("value2", test_id);
                params.put("table", "nfly_test_result");
                return params;
            }
        };
        MySingleton.getmInstance(CompanyMockTestDetailsActivity.this).addToRequestQueue(stringRequest);
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

                        questionOptionsSet.put(questionDataSet.get(i),optionsDataSet);
                    }

                    //Toast.makeText(CompanyMockTestDetailsActivity.this, quesIdDataSet.toString(), Toast.LENGTH_SHORT).show();

                    if (questionDataSet.isEmpty()){
                        Toast.makeText(CompanyMockTestDetailsActivity.this, "No Questions There", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        companyMockTestDetailsNextBtn.setVisibility(View.VISIBLE);
                        companyMockTestDetailsNextBtn.setEnabled(true);
                        companyMockTestDetailsQuestion.setText((count+1)+". "+questionDataSet.get(count));
                        companyMockTestDetailsOption1.setText(questionOptionsSet.get(questionDataSet.get(count)).get(0));
                        companyMockTestDetailsOption2.setText(questionOptionsSet.get(questionDataSet.get(count)).get(1));
                        companyMockTestDetailsOption3.setText(questionOptionsSet.get(questionDataSet.get(count)).get(2));
                        companyMockTestDetailsOption4.setText(questionOptionsSet.get(questionDataSet.get(count)).get(3));

                        userOptions = new int[questionDataSet.size()];
                        for (int i = 0; i < questionDataSet.size(); i++) {
                            userOptions[i] = 0;
                        }
                        user_score = 0;
                        max_score = 3 * questionDataSet.size();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CompanyMockTestDetailsActivity.this, "Error", Toast.LENGTH_SHORT).show();
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
                params.put("value",test_id);
                params.put("table","nfly_test_question");
                return params;
            }
        };
        MySingleton.getmInstance(CompanyMockTestDetailsActivity.this).addToRequestQueue(stringRequest);
    }


    public void onTestPreviousBtnClick(View view) {
        saveUserOption();
        count--;
        if(count==0){
            companyMockTestDetailsPreviousBtn.setVisibility(View.INVISIBLE);
            companyMockTestDetailsPreviousBtn.setEnabled(false);
        }
        if(count>0){
            companyMockTestDetailsPreviousBtn.setVisibility(View.VISIBLE);
            companyMockTestDetailsPreviousBtn.setEnabled(true);

            companyMockTestDetailsSubmitBtn.setVisibility(View.INVISIBLE);
            companyMockTestDetailsSubmitBtn.setEnabled(false);
        }
        showUserOption(count);
        companyMockTestDetailsNextBtn.setVisibility(View.VISIBLE);
        companyMockTestDetailsNextBtn.setEnabled(true);
        companyMockTestDetailsQuestion.setText((count+1)+". "+questionDataSet.get(count));
        companyMockTestDetailsOption1.setText(questionOptionsSet.get(questionDataSet.get(count)).get(0));
        companyMockTestDetailsOption2.setText(questionOptionsSet.get(questionDataSet.get(count)).get(1));
        companyMockTestDetailsOption3.setText(questionOptionsSet.get(questionDataSet.get(count)).get(2));
        companyMockTestDetailsOption4.setText(questionOptionsSet.get(questionDataSet.get(count)).get(3));
    }

    public void onTestNextBtnClick(View view) {
        saveUserOption();
        count++;
        if(count==(questionDataSet.size()-1)){
            companyMockTestDetailsNextBtn.setVisibility(View.INVISIBLE);
            companyMockTestDetailsNextBtn.setEnabled(false);

            companyMockTestDetailsSubmitBtn.setVisibility(View.VISIBLE);
            companyMockTestDetailsSubmitBtn.setEnabled(true);
        }
        if(count<(questionDataSet.size()-1)){
            companyMockTestDetailsNextBtn.setVisibility(View.VISIBLE);
            companyMockTestDetailsNextBtn.setEnabled(true);
        }
        showUserOption(count);
        companyMockTestDetailsPreviousBtn.setVisibility(View.VISIBLE);
        companyMockTestDetailsPreviousBtn.setEnabled(true);
        companyMockTestDetailsQuestion.setText((count+1)+". "+questionDataSet.get(count));
        companyMockTestDetailsOption1.setText(questionOptionsSet.get(questionDataSet.get(count)).get(0));
        companyMockTestDetailsOption2.setText(questionOptionsSet.get(questionDataSet.get(count)).get(1));
        companyMockTestDetailsOption3.setText(questionOptionsSet.get(questionDataSet.get(count)).get(2));
        companyMockTestDetailsOption4.setText(questionOptionsSet.get(questionDataSet.get(count)).get(3));
    }

    private void showUserOption(int id) {
        int userPrevOption=R.id.companyMockTestDetailsOption1;
        switch(userOptions[id]){
            case 0:
                return;
            case 1:
                userPrevOption=R.id.companyMockTestDetailsOption1;
                break;
            case 2:
                userPrevOption=R.id.companyMockTestDetailsOption2;
                break;
            case 3:
                userPrevOption=R.id.companyMockTestDetailsOption3;
                break;
            case 4:
                userPrevOption=R.id.companyMockTestDetailsOption4;
                break;
        }

        selectRadioBtn=findViewById(userPrevOption);
        selectRadioBtn.setChecked(true);
    }

    private void saveUserOption() {
        optionIdSelected=companyMockTestDetailsOptions.getCheckedRadioButtonId();
        switch(optionIdSelected){
            case R.id.companyMockTestDetailsOption1:
                userOptions[count]=1;
                break;
            case R.id.companyMockTestDetailsOption2:
                userOptions[count]=2;
                break;
            case R.id.companyMockTestDetailsOption3:
                userOptions[count]=3;
                break;
            case R.id.companyMockTestDetailsOption4:
                userOptions[count]=4;
                break;
            case -1:
                userOptions[count]=0;
        }
        companyMockTestDetailsOptions.clearCheck();
    }

    public void onTestSubmitBtnClick(View view) {
        saveUserOption();
        companyMockTestDetailsSubmitBtn.setEnabled(false);
        companyMockTestDetailsSubmitBtn.setText("Please wait...");
        //Toast.makeText(this, Integer.toString(userOptions[count]), Toast.LENGTH_SHORT).show();

        for(int i=0;i<answerDataSet.size();i++){
            if(userOptions[i]==Integer.parseInt(answerDataSet.get(i))){
                user_score=user_score+3;
            }
        }
        //Toast.makeText(this, user_score+"/"+max_score, Toast.LENGTH_SHORT).show();
        //updateTestTable();
        for(int i=0;i<quesIdDataSet.size();i++){
            int marks=0;
            if(userOptions[i]==Integer.parseInt(answerDataSet.get(i))){
                marks=3;
            }
            insertResponse(quesIdDataSet.get(i),i,marks);
        }
        insertAttempt();
    }

    private void insertAttempt() {

        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        StringRequest stringRequest=new StringRequest(Request.Method.POST,urlInsertAttempt, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject=new JSONObject(response);
                    status=arrayObject.getInt("status");
                    if(status==200){
                        Intent intent=new Intent(CompanyMockTestDetailsActivity.this,PracticeTestResultActivity.class);
                        intent.putExtra("user_score",user_score);
                        intent.putExtra("max_score",max_score);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(CompanyMockTestDetailsActivity.this, "Insertion Failed", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CompanyMockTestDetailsActivity.this, "Error", Toast.LENGTH_SHORT).show();
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
                params.put("insert_array[user_id]", user_id);
                params.put("insert_array[test_id]",test_id);
                params.put("insert_array[attempt_id]",Integer.toString(attempt_id));
                params.put("insert_array[state]","1");
                params.put("insert_array[score]",Integer.toString(user_score));
                params.put("insert_array[date]",date);
                params.put("table","nfly_test_result");
                return params;
            }
        };
        MySingleton.getmInstance(CompanyMockTestDetailsActivity.this).addToRequestQueue(stringRequest);
    }

    private void insertResponse(final String question_id, final int i, final int marks) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,urlInsertAttempt, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject arrayObject=new JSONObject(response);
                    status=arrayObject.getInt("status");
                    if(status==200){
                        //Toast.makeText(CompanyMockTestDetailsActivity.this, "Response Insertion "+ i+" Successful", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //Toast.makeText(CompanyMockTestDetailsActivity.this, "Insertion Failed", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CompanyMockTestDetailsActivity.this, "Error", Toast.LENGTH_SHORT).show();
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
                params.put("insert_array[user_id]", user_id);
                params.put("insert_array[test_id]",test_id);
                params.put("insert_array[attempt_id]",Integer.toString(attempt_id));
                params.put("insert_array[question_id]",question_id);
                params.put("insert_array[user_response]",Integer.toString(userOptions[i]));
                params.put("insert_array[marks]",Integer.toString(marks));
                params.put("table","nfly_test_response");
                return params;
            }
        };
        MySingleton.getmInstance(CompanyMockTestDetailsActivity.this).addToRequestQueue(stringRequest);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MySingleton.release();
    }
}
